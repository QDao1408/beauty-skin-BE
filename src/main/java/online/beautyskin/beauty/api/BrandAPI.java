package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Brand;
import online.beautyskin.beauty.entity.request.BrandRequest;
import online.beautyskin.beauty.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/brand")
@SecurityRequirement(name = "api")
public class BrandAPI {
    @Autowired
    private BrandService brandService;

    List<Brand> brands = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getBrand() {
        brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity updateBrand(@Valid @RequestBody Brand brand, @PathVariable long id) {
        Brand b = brandService.getBrandById(id);
        brandService.updateBrand(b);
        return ResponseEntity.ok(b);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity createBrand(@Valid @RequestBody BrandRequest brand) {
        Brand brand1 = brandService.createBrand(brand);
        return ResponseEntity.ok(brand1);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity deleteBrand(@PathVariable long id) {
        Brand b = brandService.deleteBrandById(id);
        return ResponseEntity.ok(b);
    }
}
