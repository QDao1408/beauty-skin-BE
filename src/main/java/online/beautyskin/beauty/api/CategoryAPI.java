package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Category;
import online.beautyskin.beauty.entity.request.CategoryRequest;
import online.beautyskin.beauty.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
@SecurityRequirement(name = "api")
public class CategoryAPI {
    @Autowired
    private CategoryService categoryService;

    List<Category> categories = new ArrayList<>();

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity create(@Valid @RequestBody CategoryRequest categoryRequest) {
        categories.add(categoryService.create(categoryRequest));
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/get")
    public ResponseEntity getAll() {
        categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity update(@Valid @RequestBody CategoryRequest categoryRequest, @PathVariable long id) {

        return ResponseEntity.ok(categoryService.updateCategory(id,categoryRequest));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity delete(@PathVariable long id) {
        Category category = categoryService.delete(id);
        return ResponseEntity.ok(category);
    }
}
