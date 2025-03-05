package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.OrderDetail;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.request.OrderDetailsRequest;
import online.beautyskin.beauty.entity.request.OrderRequest;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.repository.ProductRepository;
import online.beautyskin.beauty.utils.UserUtils;
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

    @Autowired
    UserUtils userUtils;

    public Order create(OrderRequest orderRequest) {
        List<OrderDetail> details = new ArrayList<>();
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setOrderDetails(details);
        order.setUser(userUtils.getCurrentUser());
//        order.setUserAddress(order.getUser().getAddresses());

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

                product.setStock(product.getStock()  - orderDetailsRequest.getQuantity());
                productRepository.save(product);

                totalPrice += orderDetail.getTotalPrice();
            }else {
                throw new RuntimeException("quantity is not enough");
            }
        }
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }
}
