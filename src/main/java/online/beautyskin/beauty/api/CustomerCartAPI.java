package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.CustomerCart;
import online.beautyskin.beauty.entity.request.CustomerCartRequest;
import online.beautyskin.beauty.service.CustomerCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/CustomerCart")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class CustomerCartAPI {
    @Autowired
    private CustomerCartService customerCartService;

    @GetMapping("/getAllCart")
    public ResponseEntity getAllCart() {
        return ResponseEntity.ok(customerCartService.findAllCart());
    }

    @GetMapping("/getCartByCustomerId/{id}")
    public ResponseEntity getCartByCustomerId(@PathVariable long id) {
        return ResponseEntity.ok(customerCartService.getCartById(id));
    }

    @PostMapping("/createCart")
    public ResponseEntity createCart(@RequestBody CustomerCartRequest customerCartRequest) {
        return ResponseEntity.ok(customerCartService.createCart(customerCartRequest));
    }
}
