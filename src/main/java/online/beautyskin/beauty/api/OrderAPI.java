package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.request.OrderRequest;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.repository.OrderRepository;
import online.beautyskin.beauty.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@SecurityRequirement(name = "api")
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) throws Exception{
        String urlPayment = orderService.create(orderRequest);
        return ResponseEntity.ok(urlPayment);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getByUser")
    public ResponseEntity getOrdersByUser() {
        List<Order> orders = orderService.getOrderByUser();
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity updateStatus(@RequestParam OrderStatusEnums status, @PathVariable long id){
        Order order = orderService.updateStatus(status,id);
        return ResponseEntity.ok(order);
    }
}
