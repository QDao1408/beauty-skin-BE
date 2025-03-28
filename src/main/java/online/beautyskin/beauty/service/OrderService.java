package online.beautyskin.beauty.service;

import online.beautyskin.beauty.api.OrderAPI;
import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.OrderDetailsRequest;
import online.beautyskin.beauty.entity.request.OrderRequest;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;
import online.beautyskin.beauty.enums.StaffTaskEnums;
import online.beautyskin.beauty.enums.TransactionEnums;
import online.beautyskin.beauty.exception.NotFoundException;
import online.beautyskin.beauty.repository.*;
import online.beautyskin.beauty.utils.UserUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderService {

    private final OrderAPI orderAPI;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserAddressRepository addressRepository;

    @Autowired
    private StaffTaskRepository staffTaskRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private LoyaltyPointService loyaltyPointService;

    OrderService(UserRepository userRepository, OrderAPI orderAPI) {
        this.userRepository = userRepository;
        this.orderAPI = orderAPI;
    }

    public String create(OrderRequest orderRequest, String promoId) throws Exception {
        List<OrderDetail> details = new ArrayList<>();
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setOrderDetails(details);

        User user = userUtils.getCurrentUser();
        order.setUser(user);

        PaymentMethod vnpay = paymentMethodRepository.findById(1);

        UserAddress userAddress = addressRepository.findFirstByUserIdAndIsDeletedFalse(user.getId())
                .orElseThrow(() -> new RuntimeException("No active address found for user"));

        order.setUserAddress(userAddress);

        double totalPrice = 0;
        order.setOrderDate(LocalDateTime.now());

        for (OrderDetailsRequest orderDetailsRequest : orderRequest.getDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findById(orderDetailsRequest.getProductId());

            if (product.getStock() >= orderDetailsRequest.getQuantity()) {
                orderDetail.setProduct(product);
                orderDetail.setQuantity(orderDetailsRequest.getQuantity());
                orderDetail.setUnitPrice(product.getPrice());
                orderDetail.setTotalPrice(product.getPrice() * orderDetailsRequest.getQuantity());
                orderDetail.setOrder(order);
                details.add(orderDetail);

                totalPrice += orderDetail.getTotalPrice();
                // update the stock after order is create
                product.setStock(product.getStock() - orderDetail.getQuantity());
                productRepository.save(product);
            } else {
                throw new RuntimeException("quantity is not enough");
            }
        }
        order.setTotalPrice(totalPrice);
        if (promoId != null) {
            long id = Long.parseLong(promoId);

            Promotion promotion = promotionRepository
                    .findAllByIdAndNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(id, 0)
                    .orElseThrow(() -> new NotFoundException("this promotion is unavailable"));
            if (order.getTotalPrice() >= promotion.getOrderPrice()) {
                double discount = getDiscountByPromotion(order, promotion);
                totalPrice = order.getTotalPrice() - discount;
                order.setTotalPrice(totalPrice);
                order.setPromotion(promotion);
                promotion.setNumOfPromo(promotion.getNumOfPromo() - 1);
                promotionRepository.save(promotion);
            }
        }
        order.setPaymentMethod(vnpay);
        order.setPaymentStatus(PaymentStatusEnums.PENDING);
        order.setOrderStatus(OrderStatusEnums.PENDING);
        // updateStatusOrder(order.getOrderStatus(), order.getId());
        Order newOrder = orderRepository.save(order);
        Transaction transaction = new Transaction();
        String des = "User " + order.getUser().getId()
                + " pay for order " + order.getId();
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setEnums(TransactionEnums.VNPAY);
        transaction.setOrders(order);
        transaction.setAmount(order.getTotalPrice());
        transaction.setDescription(des);
        transaction.setIncome(true);
        transactionRepository.save(transaction);
        // every time user create order, user total amount updated, there for rank will
        // be updated
        user.setTotalAmount(user.getTotalAmount() + totalPrice);
        loyaltyPointService.updateRankForUser(user);
        // create task for staff to assign orders
        createTask(order);
        return createURLPayment(newOrder);
    }

    public Order createCOD(OrderRequest orderRequest, String promoId) {
        List<OrderDetail> details = new ArrayList<>();
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setOrderDetails(details);

        User user = userUtils.getCurrentUser();
        order.setUser(user);

        PaymentMethod cod = paymentMethodRepository.findById(2);

        UserAddress userAddress = addressRepository.findFirstByUserIdAndIsDeletedFalse(user.getId())
                .orElseThrow(() -> new RuntimeException("No active address found for user"));

        order.setUserAddress(userAddress);

        double totalPrice = 0;
        order.setOrderDate(LocalDateTime.now());

        for (OrderDetailsRequest orderDetailsRequest : orderRequest.getDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findById(orderDetailsRequest.getProductId());

            if (product.getStock() >= orderDetailsRequest.getQuantity()) {
                orderDetail.setProduct(product);
                orderDetail.setQuantity(orderDetailsRequest.getQuantity());
                orderDetail.setUnitPrice(product.getPrice());
                orderDetail.setTotalPrice(product.getPrice() * orderDetailsRequest.getQuantity());
                orderDetail.setOrder(order);
                details.add(orderDetail);

                totalPrice += orderDetail.getTotalPrice();
                // update the stock after order is create
                product.setStock(product.getStock() - orderDetail.getQuantity());
                productRepository.save(product);
            } else {
                throw new RuntimeException("quantity is not enough");
            }
        }
        order.setTotalPrice(totalPrice);
        if (promoId != null) {
            long id = Long.parseLong(promoId);

            Promotion promotion = promotionRepository
                    .findAllByIdAndNumOfPromoIsGreaterThanAndIsOutDateFalseAndIsDeletedFalse(id, 0)
                    .orElseThrow(() -> new NotFoundException("this promotion is unavailable"));
            if (order.getTotalPrice() >= promotion.getOrderPrice()) {
                double discount = getDiscountByPromotion(order, promotion);
                totalPrice = order.getTotalPrice() - discount;
                order.setTotalPrice(totalPrice);
                order.setPromotion(promotion);
                promotion.setNumOfPromo(promotion.getNumOfPromo() - 1);
                promotionRepository.save(promotion);
            }
        }
        order.setPaymentMethod(cod);
        order.setPaymentStatus(PaymentStatusEnums.PENDING);
        order.setOrderStatus(OrderStatusEnums.PENDING);
        orderRepository.save(order);

        // create task for staff to assign orders
        createTask(order);
        return order;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByUser() {
        User user = userUtils.getCurrentUser();
        return orderRepository.findAllByUserId((user.getId()));
    }

    public String createURLPayment(Order order) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);
        String orderId = UUID.randomUUID().toString().substring(0, 6);

        String tmnCode = "8FDRRU1S";
        String secretKey = "2LS6HWZV3VANKGVZW4IFO7L0J8A3406K";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        // String returnURL =
        // "http://beautyskinshop.online/checkout/payment-result/?orderId="+order.getId();
        String returnURL = "http://beautyskinshop.online/checkout/payment-result/?orderId=" + order.getId();

        String currCode = "VND";
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", orderId);
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + orderId);
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount", (int) order.getTotalPrice() + "00");
        vnpParams.put("vnp_ReturnUrl", returnURL);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "167.99.74.201");

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'
        return urlBuilder.toString();
    }

    private String generateHMAC(String secretKey, String signData)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha512 = Mac.getInstance("HmacSHA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        hmacSha512.init(keySpec);
        byte[] hmacBytes = hmacSha512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    public void createTask(Order order) {
        StaffTask staffTask = new StaffTask();
        staffTask.setOrder(order);
        staffTask.setStaffTaskEnums(StaffTaskEnums.UNASSIGNED);
        staffTask.setLastUpdate(LocalDateTime.now());
        staffTaskRepository.save(staffTask);
    }

    public Order updateStatusOrder(OrderStatusEnums status, long id) {
        Order order = orderRepository.findOrderById(id);
        order.setOrderStatus(status);
        if (status == OrderStatusEnums.IN_PROGRESS) {
            StaffTask staffTask1 = staffTaskRepository.findByOrder(order);
            staffTask1.setStaff(userUtils.getCurrentUser());
            staffTask1.setLastUpdate(LocalDateTime.now());
            staffTask1.setStaffTaskEnums(StaffTaskEnums.IN_PROGRESS);
            staffTaskRepository.save(staffTask1);
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                Product product = orderDetail.getProduct();
                product.setStock(product.getStock() - orderDetail.getQuantity());
                productRepository.save(product);
            }

        } else if (status == OrderStatusEnums.SHIPPED) {
            StaffTask staffTask2 = staffTaskRepository.findByOrder(order);
            staffTask2.setLastUpdate(LocalDateTime.now());
            staffTask2.setStaffTaskEnums(StaffTaskEnums.SHIPPED);
            staffTaskRepository.save(staffTask2);
        } else if (status == OrderStatusEnums.CANCELLED) {
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                Product product = orderDetail.getProduct();
                product.setStock(product.getStock() + orderDetail.getQuantity());
                productRepository.save(product);
            }
            // every time user cancel order, user's total amount will be updated, there fore
            // user rank will be updated
            User user = order.getUser();
            // update total amount
            user.setTotalAmount(user.getTotalAmount() - order.getTotalPrice());
            // update rank
            loyaltyPointService.updateRankForUser(user);
            userRepository.save(user);
            // update promotion
            Promotion promotion = order.getPromotion();
            if (promotion != null) {
                promotion.setNumOfPromo(promotion.getNumOfPromo() + 1);
                promotionRepository.save(promotion);
            }
            // create refund transaction
            transactionService.createRefundTransaction(order);
        } else if (status == OrderStatusEnums.DELIVERED) {
            StaffTask staffTask3 = staffTaskRepository.findByOrder(order);
            staffTask3.setLastUpdate(LocalDateTime.now());
            staffTask3.setStaffTaskEnums(StaffTaskEnums.DELIVERED);
            staffTaskRepository.save(staffTask3);
            if (order.getPaymentMethod().getId() == 2) {
                // Record transaction when cod order is delivered
                Transaction transaction = new Transaction();
                String des = "User " + order.getUser().getId()
                        + " pay for order " + order.getId();
                transaction.setTransactionDate(LocalDateTime.now());
                transaction.setEnums(TransactionEnums.COD);
                transaction.setOrders(order);
                transaction.setAmount(order.getTotalPrice());
                transaction.setDescription(des);
                transaction.setIncome(true);
                transactionRepository.save(transaction);
            }
        } else if(status == OrderStatusEnums.REFUNDED) {
            // create transaction for refund
            Transaction transaction = new Transaction();
                String des = "Refund order " + order.getId()
                        + " for user " + order.getUser().getId();
                transaction.setTransactionDate(LocalDateTime.now());
                transaction.setEnums(TransactionEnums.REFUND);
                transaction.setOrders(order);
                transaction.setAmount(order.getTotalPrice());
                transaction.setDescription(des);
                transaction.setIncome(false);
                transactionRepository.save(transaction);
        }
        return orderRepository.save(order);
    }

    public Order updateStatusPayment(PaymentStatusEnums status, long id) {
        Order order = orderRepository.findOrderById(id);
        order.setPaymentStatus(status);
        return orderRepository.save(order);
    }

    public Order getLastedOrder() {
        User user = userUtils.getCurrentUser();
        List<Order> order = orderRepository.findOrderByUserId(user.getId());
        Order lastOrder = order.get(order.size() - 1);
        if (order == null) {
            throw new RuntimeException("Người dùng chưa có đơn hàng nào");
        } else {
            return lastOrder;
        }
    }

    public void cancelOrder(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("Order không tồn tại");
        } else {
            order.setOrderStatus(OrderStatusEnums.CANCELLED);
            orderRepository.save(order);
        }
    }

    public double getDiscountByPromotion(Order order, Promotion promo) {
        double amount = 0;
        double discount = promo.getPromoAmount();
        if (discount < 1) {
            amount = order.getTotalPrice() * discount;
        } else {
            amount = discount;
        }
        return amount;
    }

}
