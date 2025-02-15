package online.beautyskin.beauty.api;

import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")
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
    public ResponseEntity createProduct(@Valid @RequestBody Product product) {
        products.add(productService.createProduct(product));
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable long id, @Valid @RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id, @Valid @RequestBody Product product) {
        Product p = productService.deleteProduct(id);
        return ResponseEntity.ok(p);
    }
}
