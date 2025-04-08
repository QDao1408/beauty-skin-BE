package online.beautyskin.beauty.service;

import online.beautyskin.beauty.api.OrderAPI;
import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.OrderDetailsRequest;
import online.beautyskin.beauty.entity.request.OrderRequest;
import online.beautyskin.beauty.entity.respone.OrderDetailResponse;
import online.beautyskin.beauty.entity.respone.OrderProductResponse;
import online.beautyskin.beauty.entity.respone.OrderResponse;
import online.beautyskin.beauty.entity.respone.OrderTransactionResponse;
import online.beautyskin.beauty.entity.respone.OrderUserResponse;
import online.beautyskin.beauty.enums.*;
import online.beautyskin.beauty.exception.NotFoundException;
import online.beautyskin.beauty.repository.*;
import online.beautyskin.beauty.utils.UserUtils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
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
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private UserRank userRank;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

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
                if(product.getStock() == 0) {
                    product.setStatus(ProductEnums.OUT_OF_STOCK);
                }
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
        order.setPaymentMethod(PaymentMethodEnums.VNPAY);
        order.setPaymentStatus(PaymentStatusEnums.PENDING);
        order.setOrderStatus(OrderStatusEnums.PENDING);
        // updateStatusOrder(order.getOrderStatus(), order.getId());
        Order newOrder = orderRepository.save(order);
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
                if(product.getStock() == 0) {
                    product.setStatus(ProductEnums.OUT_OF_STOCK);
                }
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
        order.setPaymentMethod(PaymentMethodEnums.COD);
        order.setPaymentStatus(PaymentStatusEnums.PENDING);
        order.setOrderStatus(OrderStatusEnums.PENDING);
        orderRepository.save(order);
        // create task for staff to assign orders
        createTask(order);
        return order;
    }

//        public List<Order> getAll() {
//        List<Order> orders = orderRepository.findAll();
//        return orders;
//    }
    public List<Order> getAll() {
        Pageable pageable = PageRequest.of(0, 60);  // Trang đầu (0), giới hạn 40 đơn hàng
        return orderRepository.findTop60LatestOrders(pageable);
    }

    public List<Order> getOrderByUser() {
        User user = userUtils.getCurrentUser();
        List<Order> orders = orderRepository.findAllByUserId((user.getId()));
        return orders;
    }

    public Order getLastedOrder() {
        User user = userUtils.getCurrentUser();
        List<Order> order = orderRepository.findOrderByUserId(user.getId());
        if(order.size() == 0) {
            throw new RuntimeException("Người dùng chưa có đơn hàng nào");
        } else {
            return order.get(order.size() - 1);
        }
    }

    public List<OrderResponse> getAll2() {
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> responses = new ArrayList<>();
        for(Order order : orders) {
            OrderResponse orderResponse = mappingOrderResponse(order);
            responses.add(orderResponse);
        }
        return responses;
    }

    public OrderUserResponse mappingOrderUserResponse(User user) {
        OrderUserResponse orderUserResponse = new OrderUserResponse();
        orderUserResponse.setFullName(user.getFullName());
        orderUserResponse.setId(user.getId());
        orderUserResponse.setMail(user.getMail());
        orderUserResponse.setPhone(user.getPhone());
        return orderUserResponse;
    }

    public OrderProductResponse mappingOrderProductResponse(Product product) {
        OrderProductResponse orderProductResponse = new OrderProductResponse();
        orderProductResponse.setCategory(product.getCategory().getName());
        orderProductResponse.setId(product.getId());
        orderProductResponse.setImage(product.getImages().getFirst());
        orderProductResponse.setName(product.getName());
        orderProductResponse.setPrice(product.getPrice());
        orderProductResponse.setPromotion(product.getPromotion());
        return orderProductResponse;
    }

    public List<OrderDetailResponse> mappingOrderDetailResponse(List<OrderDetail> orderDetails) {
        List<OrderDetailResponse> responses = new ArrayList<>();
        OrderDetailResponse response = new OrderDetailResponse();
        for(OrderDetail orderDetail : orderDetails) {
            response.setId(orderDetail.getOrderDetailId());
            response.setOrderProductResponse(mappingOrderProductResponse(orderDetail.getProduct()));
            response.setQuantity(orderDetail.getQuantity());
            response.setTotalPrice(orderDetail.getTotalPrice());
            response.setUnitPrice(orderDetail.getUnitPrice());
            responses.add(response);
        }
        return responses;
    }

    public OrderResponse mappingOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setOrderDate(order.getOrderDate());
        response.setOrderDetails(mappingOrderDetailResponse(order.getOrderDetails()));
        response.setOrderStatus(order.getOrderStatus().toString());
        response.setPaymentMethod(order.getPaymentMethod().toString());
        response.setPaymentStatus(order.getPaymentStatus().toString());
        response.setOrderTransactions(mappingOrderTransactionResponse(order.getTransactions()));
        if(order.getPromotion() != null) {
            response.setPromotion(order.getPromotion().getName());
        } else {
            response.setPromotion("");
        }

        response.setTotalPrice(order.getTotalPrice());
        response.setUserResponse(mappingOrderUserResponse(order.getUser()));
        return response;
    }

    public List<OrderTransactionResponse> mappingOrderTransactionResponse(List<Transaction> transactions) {
        List<OrderTransactionResponse> responses = new ArrayList<>();
        OrderTransactionResponse response = new OrderTransactionResponse();
        for(Transaction transaction : transactions) {
            response.setTransactionEnum(transaction.getEnums().toString());
            response.setTransactionDate(transaction.getTransactionDate());
            response.setTransactionAmount(transaction.getAmount());
            responses.add(response);
        }
        return responses;
    }

//    public List<OrderResponse> getOrderByUser() {
//        User user = userUtils.getCurrentUser();
//        List<Order> orders = orderRepository.findAllByUserId((user.getId()));
//        List<OrderResponse> responses = new ArrayList<>();
//        for(Order order : orders) {
//            OrderResponse orderResponse = mappingOrderResponse(order);
//            System.out.println(orderResponse.getUserResponse().getFullName());
//            responses.add(orderResponse);
//        }
//        return responses;
//    }



    public String createURLPayment(Order order) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);
        String orderId = UUID.randomUUID().toString().substring(0, 6);

        String tmnCode = "WERG9TU5";
        String secretKey = "V5CBL4IW8SZ8SFHES5FDPEGM6SRIEWTJ";
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
        if (status == OrderStatusEnums.IN_PROGRESS) {// xử lí đơn hàng
            updateOrderInProgress(id);
        } else if (status == OrderStatusEnums.SHIPPING) {// đơn hàng đang được giao
            updateOrderShipped(id);
        } else if (status == OrderStatusEnums.CANCELLED) {// user phải hủy đơn hàng trc khi đơn hàng dc xử lí
            updateOrderCancelled(id);
        } else if (status == OrderStatusEnums.DELIVERED) {// đơn hàng đã đc giao tới user
            updateOrderDelivered(id);
        } else if (status == OrderStatusEnums.CONFIRMED) {// user xác nhận đơn hàng, sau đó kh dc refund
            updateOrderConfirmed(id);
        } else if (status == OrderStatusEnums.REFUND_REQ) {// user gửi yêu cấu refund sp, chờ manager duyệt
            updateOrderRefundRequest(id);
        } else if (status == OrderStatusEnums.REFUNDED) {// đơn được duyệt, trả tiền cho user, manager chỉnh stock lại =
            // tay
            updateOrderRefunded(id);
        }
        return orderRepository.save(order);
    }

    public Order updateStatusPayment(PaymentStatusEnums status, long id) {
        Order order = orderRepository.findOrderById(id);
        order.setPaymentStatus(status);
        if (status == PaymentStatusEnums.PAID){
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
        }

        if (status == PaymentStatusEnums.CANCELLED) {
            List<OrderDetail> orderDetails = order.getOrderDetails();
            for (OrderDetail orderDetail : orderDetails) {
                Product product = orderDetail.getProduct();
                product.setStock(product.getStock() + orderDetail.getQuantity());
                productRepository.save(product); // Lưu lại số lượng stock mới
            }

            StaffTask staffTask = staffTaskRepository.findByOrder(order);
            if (staffTask != null) {
                staffTaskRepository.delete(staffTask);
            }
            orderRepository.delete(order);
            for (OrderDetail orderDetail : orderDetails) {
                orderDetailRepository.delete(orderDetail);
            }
            return null;
        }
        return orderRepository.save(order);
    }



    public void cancelOrder(long orderId) {
//        Order order = orderRepository.findOrderById(orderId);
//        if (order == null) {
//            throw new RuntimeException("Order không tồn tại");
//        } else {
//            order.setOrderStatus(OrderStatusEnums.CANCELLED);
//            orderRepository.save(order);
//        }
        Order order = orderRepository.findOrderById(orderId);
        if (order.getOrderStatus() == OrderStatusEnums.PENDING) {
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                Product product = orderDetail.getProduct();
                product.setStock(product.getStock() + orderDetail.getQuantity());
                productRepository.save(product);
            }
            // update promotion
            Promotion promotion = order.getPromotion();
            if (promotion != null) {
                promotion.setNumOfPromo(promotion.getNumOfPromo() + 1);
                promotionRepository.save(promotion);
            }
            // create refund transaction
            transactionService.createRefundTransaction(order);
            order.setOrderStatus(OrderStatusEnums.CANCELLED);
            List<OrderDetail> orderDetails = order.getOrderDetails();
            for (OrderDetail orderDetail : orderDetails) {
                Product product = orderDetail.getProduct();
                product.setStock(product.getStock() + orderDetail.getQuantity());
                productRepository.save(product);
            }
            orderRepository.save(order);
        } else {
            throw new NotFoundException("Đơn hàng đang được xử lí, bạn không thể hủy đơn hàng");
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

    public Order updateOrderInProgress(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        // update staff task
        StaffTask staffTask1 = staffTaskRepository.findByOrder(order);
        staffTask1.setStaff(userUtils.getCurrentUser());
        staffTask1.setLastUpdate(LocalDateTime.now());
        staffTask1.setStaffTaskEnums(StaffTaskEnums.IN_PROGRESS);
        staffTaskRepository.save(staffTask1);
        return order;
    }

    public Order updateOrderShipped(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setOrderStatus(OrderStatusEnums.SHIPPING);
        long COD = 2l; // COD id is 2
        // update staff task
        StaffTask staffTask2 = staffTaskRepository.findByOrder(order);
        staffTask2.setLastUpdate(LocalDateTime.now());
        staffTask2.setStaffTaskEnums(StaffTaskEnums.SHIPPING);
        staffTaskRepository.save(staffTask2);
        // create transaction if payment method is COD
        if (order.getPaymentMethod().equals("COD")) {
            // shipper will pay the order and collect back form customer when delivered
            order.setPaymentStatus(PaymentStatusEnums.PAID);
            // save into transaction
            transactionService.createTransactionForCreateOrder(order, TransactionEnums.COD);
            userRank.updateRankForUser(order.getUser());
        }
        return orderRepository.save(order);
    }

    public Order updateOrderDelivered(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setOrderStatus(OrderStatusEnums.DELIVERED);
        // update staff task
        StaffTask staffTask3 = staffTaskRepository.findByOrder(order);
        staffTask3.setLastUpdate(LocalDateTime.now());
        staffTask3.setStaffTaskEnums(StaffTaskEnums.DELIVERED);
        staffTaskRepository.save(staffTask3);
        return orderRepository.save(order);
    }

    // bug cancel chưa cộng
    public Order updateOrderCancelled(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order.getOrderStatus() != OrderStatusEnums.PENDING) {
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                Product product = orderDetail.getProduct();
                product.setStock(product.getStock() + orderDetail.getQuantity());
                productRepository.save(product);
            }
            // update promotion
            Promotion promotion = order.getPromotion();
            if (promotion != null) {
                promotion.setNumOfPromo(promotion.getNumOfPromo() + 1);
                promotionRepository.save(promotion);
            }
            // create refund transaction
            transactionService.createRefundTransaction(order);
            return orderRepository.save(order);
        } else {
            throw new NotFoundException("Đơn hàng đang được xử lí, bạn không thể hủy đơn hàng");
        }

    }

    public Order updateOrderRefundRequest(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setOrderStatus(OrderStatusEnums.REFUND_REQ);
        return orderRepository.save(order);
    }

    public Order updateOrderRefunded(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setOrderStatus(OrderStatusEnums.REFUNDED);
        transactionService.createRefundTransaction(order);
        return orderRepository.save(order);
    }

    private Order updateOrderConfirmed(long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        order.setOrderStatus(OrderStatusEnums.CONFIRMED);

        User user = order.getUser();
        user.setTotalAmount(user.getTotalAmount() + order.getTotalPrice());
        userRank.updateRankForUser(user);

        return orderRepository.save(order);
    }

    @Scheduled(fixedRate = 60000 * 60) // repeat every hour
    public void updateUnconfirmedOrder() {
        List<Order> orders = orderRepository.findByOrderStatus(OrderStatusEnums.DELIVERED);
        LocalDateTime now = LocalDateTime.now();

        for (Order order : orders) {
            LocalDateTime orderTime = order.getOrderDate();
            // 3 ngày kể từ ngày nhận hàng, đơn hàng sẽ tư update thành confirmed
            if (orderTime.isBefore(now.minusDays(3)) || orderTime.isEqual(now.minusDays(3))) {
                updateOrderConfirmed(order.getId());
            }
        }
    }
}
