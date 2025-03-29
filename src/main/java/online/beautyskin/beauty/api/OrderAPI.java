package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Order;
import online.beautyskin.beauty.entity.request.OrderRequest;
import online.beautyskin.beauty.enums.OrderStatusEnums;
import online.beautyskin.beauty.enums.PaymentStatusEnums;
import online.beautyskin.beauty.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@SecurityRequirement(name = "api")
public class OrderAPI {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest, @RequestParam(required = false) String promoId) throws Exception{
        String urlPayment = orderService.create(orderRequest, promoId);
        return ResponseEntity.ok(urlPayment);
    }

    @PostMapping("/createCOD")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity createCOD(@RequestBody OrderRequest orderRequest, @RequestParam(required = false) String promoId){
        return ResponseEntity.ok(orderService.createCOD(orderRequest, promoId));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getAll() {
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/getByUser")
    @PreAuthorize("hasAnyAuthority('User')")
    public ResponseEntity getOrdersByUser() {
        List<Order> orders = orderService.getOrderByUser();
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/updateStatusOrder/{orderId}")
    @PreAuthorize("hasAnyAuthority('STAFF')")
    public ResponseEntity updateStatus(@RequestParam OrderStatusEnums status, @PathVariable long orderId){
        Order order = orderService.updateStatusOrder(status,orderId);
        return ResponseEntity.ok(order);
    }

    @PatchMapping("/updateStatusPayment/{orderId}")
    public ResponseEntity updateStatusPayment(@RequestParam PaymentStatusEnums status, @PathVariable long orderId){
        Order order = orderService.updateStatusPayment(status,orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("getLastedOrder")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity getLastedOrder(){
        return ResponseEntity.ok(orderService.getLastedOrder());
    }

    @PutMapping("cancelOrder/{orderId}")
    @PreAuthorize("hasAnyAuthority('MANAGER','USER')")
    public ResponseEntity cancelOrder(@RequestParam long orderId){
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order đã được hủy!!!");
    }

}
