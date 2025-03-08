package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.Image;
import online.beautyskin.beauty.entity.request.ImageRequest;
import online.beautyskin.beauty.repository.ImageRepository;
import online.beautyskin.beauty.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/image")
@SecurityRequirement(name = "api")
public class ImageAPI {

    @Autowired
    private ImageService service;

    @GetMapping("/getAll")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity update(@PathVariable long id, @RequestBody ImageRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity create(@RequestBody ImageRequest request) {
        return ResponseEntity.ok(service.create(request));
    }
}
