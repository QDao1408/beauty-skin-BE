package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @PostMapping("/{userId}/add/{productId}/{quantity}")
    public ResponseEntity<String> addToCart(@PathVariable Long userId, @PathVariable Long productId, @PathVariable int quantity) {
        customerCartService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok("Product added to cart!");
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        customerCartService.removeProductFromCart(userId, productId);
        return ResponseEntity.ok("Product removed from cart!");
    }

    @GetMapping("/showCart")
    public ResponseEntity showCart() {
        return ResponseEntity.ok(customerCartService.getCart());
    }
}
