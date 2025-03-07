package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.RoutineStep;
import online.beautyskin.beauty.entity.request.RoutineRequest;
import online.beautyskin.beauty.entity.request.RoutineStepRequest;
import online.beautyskin.beauty.service.RoutineService;
import online.beautyskin.beauty.service.RoutineStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/routineStep")
@SecurityRequirement(name = "api")
public class RoutineStepAPI {


    @Autowired
    private RoutineStepService service;

    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity create(@Valid @RequestBody RoutineStepRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity update(@PathVariable long id, @Valid @RequestBody RoutineStepRequest request) {
        return ResponseEntity.ok(service.update(id,request));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity delete(@PathVariable long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
