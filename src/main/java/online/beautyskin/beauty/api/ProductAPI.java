package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Product;
import online.beautyskin.beauty.entity.request.ProductRequest;
import online.beautyskin.beauty.entity.respone.ProductResponse;
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
        List<ProductResponse> productResponse = productService.getAllProducts();
        return ResponseEntity.ok(productResponse);
    }

    @GetMapping("/getByName")
    public ResponseEntity getProductsByName(@RequestParam String name) {
        products = productService.getProductsByName(name);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getFormCate/{id}")
    public ResponseEntity getProductFromCate(@PathVariable long id) {
        List<ProductResponse> products = productService.getFromCateId(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getBySkinTypes/{id}")
    public ResponseEntity getProductFromSkinType(@PathVariable long id) {
        List<ProductResponse> products = productService.getBySkinType(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getBySkinConcerns/{id}")
    public ResponseEntity getProductFromSkinConcern(@PathVariable long id) {
        products = productService.getBySkinConcern(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getByTags/{id}")
    public ResponseEntity getProductFromTag(@PathVariable long id) {
        products = productService.getByTag(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getByRoutineStep/{id}")
    public ResponseEntity getProductFromRoutineStep(@PathVariable long id) {
        products = productService.getByRoutineStep(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getByForm/{id}")
    public ResponseEntity getProductFromForm(@PathVariable long id) {
        products = productService.getByForm(id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getProductById(@PathVariable long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.ok(productResponse);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity createProduct(@Valid @RequestBody ProductRequest product) {
        Product p = productService.createProduct(product);
        return ResponseEntity.ok(p);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity updateProduct(@PathVariable long id, @Valid @RequestBody ProductRequest product) {
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity deleteProduct(@PathVariable long id) {
        Product p = productService.deleteProduct(id);
        return ResponseEntity.ok(p);
    }
}
