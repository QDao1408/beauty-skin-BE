package online.beautyskin.beauty.api;

import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Category;
import online.beautyskin.beauty.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryAPI {
    @Autowired
    private CategoryService categoryService;

    List<Category> categories = new ArrayList<>();

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody Category category) {
        categories.add(categoryService.create(category));
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/get")
    public ResponseEntity getAll() {
        categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@Valid @RequestBody Category category, @PathVariable long id) {
        categoryService.create(category);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable long id) {
        Category category = categoryService.findById(id);
        category.setDeleted(true);
        return ResponseEntity.ok(category);
    }
}
