package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.request.RoutineRequest;
import online.beautyskin.beauty.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routine")
@SecurityRequirement(name = "api")
public class RoutineAPI {

    @Autowired
    private RoutineService service;

    @GetMapping("/get")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity create(@Valid @RequestBody RoutineRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity update(@PathVariable long id, @Valid @RequestBody RoutineRequest request) {
        return ResponseEntity.ok(service.update(id,request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
