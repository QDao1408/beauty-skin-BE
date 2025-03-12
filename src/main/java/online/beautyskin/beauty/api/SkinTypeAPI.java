package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.entity.SkinType;
import online.beautyskin.beauty.entity.request.SkinTypeRequest;
import online.beautyskin.beauty.service.SkinTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/skinType")
@SecurityRequirement(name = "api")
public class SkinTypeAPI {

    List<SkinType> types = new ArrayList<>();

    @Autowired
    private SkinTypeService service;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity create(@RequestBody SkinTypeRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        types = service.getAll();
        return ResponseEntity.ok(types);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity update(@PathVariable long id, @RequestBody SkinTypeRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }



}
