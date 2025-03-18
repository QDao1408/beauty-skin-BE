package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.*;
import online.beautyskin.beauty.entity.request.OrderDetailsRequest;
import online.beautyskin.beauty.entity.request.OrderRequest;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;
import online.beautyskin.beauty.enums.StaffTaskEnums;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.repository.StaffTaskRepository;
import online.beautyskin.beauty.repository.UserAddressRepository;
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

import static online.beautyskin.beauty.enums.OrderStatusEnums.CREATED;
import static online.beautyskin.beauty.enums.OrderStatusEnums.IN_PROGRESS;
import static online.beautyskin.beauty.enums.PaymentStatusEnums.PENDING;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserAddressRepository addressRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private StaffTaskRepository staffTaskRepository;


    public String create(OrderRequest orderRequest) throws Exception{
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

        for (OrderDetailsRequest orderDetailsRequest: orderRequest.getDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findById(orderDetailsRequest.getProductId());

            if (product.getStock() >= orderDetailsRequest.getQuantity()) {
                orderDetail.setProduct(product);
                orderDetail.setQuantity(orderDetailsRequest.getQuantity());
                orderDetail.setUnitPrice(product.getPrice());
                orderDetail.setTotalPrice(product.getPrice() * orderDetailsRequest.getQuantity());
                orderDetail.setOrder(order);
                details.add(orderDetail);

//                product.setStock(product.getStock() - orderDetail.getQuantity());
//                productRepository.save(product);

                totalPrice += orderDetail.getTotalPrice();
            }else {
                throw new RuntimeException("quantity is not enough");
            }
        }
        order.setTotalPrice(totalPrice);
        order.setPaymentStatus(PaymentStatusEnums.PENDING);
        order.setOrderStatus(OrderStatusEnums.PENDING);
        Order newOrder = orderRepository.save(order);
        return createURLPayment(newOrder);
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public List<Order> getOrderByUser() {
        User user = userUtils.getCurrentUser();
        return orderRepository.findAllByUserId((user.getId()));
    }

    public String createURLPayment(Order order)throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);
        String orderId = UUID.randomUUID().toString().substring(0, 6);

        String tmnCode = "8FDRRU1S";
        String secretKey = "2LS6HWZV3VANKGVZW4IFO7L0J8A3406K";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
//        String returnURL = "http://beautyskinshop.online/checkout/payment-result/?orderId="+order.getId();
        String returnURL = "http://beautyskinshop.online/checkout/payment-result/?orderId="+order.getId();

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

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
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

    public Order updateStatusOrder(OrderStatusEnums status, long id) {
        Order order = orderRepository.findOrderById(id);
        order.setOrderStatus(status);
        if (status == OrderStatusEnums.CREATED){
            StaffTask staffTask = new StaffTask();
            for (OrderDetail orderDetail : order.getOrderDetails()){
                Product product = orderDetail.getProduct();
                product.setStock(product.getStock() - orderDetail.getQuantity());
                productRepository.save(product);
            }
            staffTask.setOrder(order);
            staffTask.setStaffTaskEnums(StaffTaskEnums.UNASSIGNED);
            staffTask.setLastUpdate(LocalDateTime.now());
            staffTaskRepository.save(staffTask);

        } else if(status == OrderStatusEnums.IN_PROGRESS) {
            StaffTask staffTask1 = staffTaskRepository.findByOrder(order);
            staffTask1.setStaff(userUtils.getCurrentUser());
            staffTask1.setLastUpdate(LocalDateTime.now());
            staffTask1.setStaffTaskEnums(StaffTaskEnums.IN_PROGRESS);
            staffTaskRepository.save(staffTask1);

        } else if(status == OrderStatusEnums.SHIPPED) {
            StaffTask staffTask2 = staffTaskRepository.findByOrder(order);
            staffTask2.setLastUpdate(LocalDateTime.now());
            staffTask2.setStaffTaskEnums(StaffTaskEnums.DONE);
            staffTaskRepository.save(staffTask2);

        } else if (status == OrderStatusEnums.CANCELLED) {
            for (OrderDetail orderDetail : order.getOrderDetails()){
                Product product = orderDetail.getProduct();
                product.setStock(product.getStock() + orderDetail.getQuantity());
                productRepository.save(product);
            }
        }
        return orderRepository.save(order);
    }

    public Order updateStatusPayment(PaymentStatusEnums status, long id) {
        Order order = orderRepository.findOrderById(id);
        order.setPaymentStatus(status);
        return orderRepository.save(order);
    }
}
