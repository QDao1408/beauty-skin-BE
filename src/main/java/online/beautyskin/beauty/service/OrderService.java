package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.OrderDetail;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.request.OrderDetailsRequest;
import online.beautyskin.beauty.entity.request.OrderRequest;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    public Order create(OrderRequest orderRequest) {
        List<OrderDetail> details = new ArrayList<>();
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setOrderDetails(details);
        order.setOrderDate(LocalDateTime.now());
        for (OrderDetailsRequest orderDetailsRequest: orderRequest.getDetails()) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findById(orderDetailsRequest.getProductId());
            orderDetail.setProduct(product);
            orderDetail.setQuantity(orderDetailsRequest.getQuantity());
            orderDetail.setUnitPrice(product.getPrice());
            orderDetail.setOrder(order);
            details.add(orderDetail);
        }
        return orderRepository.save(order);
    }
}
