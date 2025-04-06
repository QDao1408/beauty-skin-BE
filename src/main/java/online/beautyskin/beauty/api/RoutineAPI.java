package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Routine;
import online.beautyskin.beauty.entity.RoutineStep;
import online.beautyskin.beauty.entity.request.RoutineRequest;
import online.beautyskin.beauty.entity.request.RoutineStepRequest;
import online.beautyskin.beauty.entity.respone.RoutineResponse;
import online.beautyskin.beauty.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routine")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class RoutineAPI {

    @Autowired
    private RoutineService service;

    @Autowired
    private RoutineService routineService;

    @PostMapping("/createRoutine")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity createRoutine(@RequestBody RoutineRequest routine) {
        Routine newRoutine = routineService.createRoutine(routine);
        return ResponseEntity.ok(newRoutine);
    }

    @PutMapping("/update/{id}/{skinTypeId}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity updateRoutine(@PathVariable Long id,@PathVariable Long skinTypeId, @RequestParam String name, @RequestParam String description) {
        Routine newRoutine = routineService.updateRoutine(id,skinTypeId,name,description);
        return ResponseEntity.ok(newRoutine);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.ok("Routine deleted");
    }

    @PostMapping("/createRoutineStep/{routineId}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity createRoutineStep(@PathVariable Long routineId, @RequestBody RoutineStepRequest routineStepRequest) {
        RoutineStep routineStep = routineService.createRoutineStep(routineId, routineStepRequest);
        return ResponseEntity.ok(routineStep);
    }

    @PutMapping("/updateRoutineStep/{routineStepId}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity updateRoutineStep(@PathVariable Long routineStepId, @RequestBody RoutineStepRequest routineStepRequest) {
        RoutineStep newRoutineStep = routineService.updateRoutineStep(routineStepId, routineStepRequest);
        return ResponseEntity.ok(newRoutineStep);
    }

    @DeleteMapping("/deleteRoutineStep/{routineStepId}")
    public ResponseEntity deleteRoutineStep(@PathVariable Long routineStepId) {
        routineService.deleteRoutineStep(routineStepId);
        return ResponseEntity.ok("Routine step deleted");
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        List<RoutineResponse> routineResponses = routineService.getAll();
        return ResponseEntity.ok(routineResponses);
    }

    @GetMapping("/getRoutineBySkinType/{skinTypeId}")
    public ResponseEntity getRoutineBySkinType(@RequestParam Long skinTypeId) {
        return ResponseEntity.ok(routineService.getRoutine(skinTypeId));
    }

    @GetMapping("/getRoutineByCurrentUser")
    public ResponseEntity getRoutineByCurrentUser() {
        return ResponseEntity.ok(routineService.getRoutineByCurrentUser());
    }

    @GetMapping("/getRoutineById/{id}")
    public ResponseEntity getRoutineById(@PathVariable Long id) {
        return ResponseEntity.ok(routineService.getRoutineById(id));
    }
}
