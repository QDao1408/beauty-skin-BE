package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.request.TagRequest;
import online.beautyskin.beauty.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tag")
@SecurityRequirement(name = "api")
public class TagAPI {

    @Autowired
    private TagService service;


    @GetMapping("/getAll")
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity create(@Valid @RequestBody TagRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity update(@PathVariable long id, @Valid @RequestBody TagRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
