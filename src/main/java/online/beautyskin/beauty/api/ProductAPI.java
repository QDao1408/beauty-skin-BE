package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.request.ProductRequest;
import online.beautyskin.beauty.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@SecurityRequirement(name = "api")
public class ProductAPI {
    List<Product> products = new ArrayList<Product>();

    @Autowired
    ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProducts() {
        products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity createProduct(@Valid @RequestBody ProductRequest product) {
        Product p = productService.createProduct(product);
        return ResponseEntity.ok(p);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity updateProduct(@PathVariable long id, @Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity deleteProduct(@PathVariable long id) {
        Product p = productService.deleteProduct(id);
        return ResponseEntity.ok(p);
    }
}
