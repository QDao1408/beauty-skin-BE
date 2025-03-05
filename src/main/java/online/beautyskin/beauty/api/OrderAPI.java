package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.request.OrderRequest;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "api")
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.create(orderRequest);
        return ResponseEntity.ok(order);
    }
}
