package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Routine;
import online.beautyskin.beauty.entity.RoutineStep;
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

    @Autowired
    private RoutineService routineService;

    @PostMapping("/createRoutine")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity createRoutine(@RequestBody RoutineRequest routine) {
        Routine newRoutine = routineService.createRoutine(routine);
        return ResponseEntity.ok(newRoutine);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRoutine(@PathVariable Long id, @RequestBody Routine routine) {
        return ResponseEntity.ok(routineService.updateRoutine(id, routine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRoutine(@PathVariable Long id) {
        routineService.deleteRoutine(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{routineId}/steps")
    public ResponseEntity createRoutineStep(@PathVariable Long routineId, @RequestBody RoutineStep routineStep) {
        return ResponseEntity.ok(routineService.createRoutineStep(routineId, routineStep));
    }

    @PutMapping("/steps/{id}")
    public ResponseEntity updateRoutineStep(@PathVariable Long id, @RequestBody RoutineStep routineStep) {
        return ResponseEntity.ok(routineService.updateRoutineStep(id, routineStep));
    }

    @DeleteMapping("/steps/{id}")
    public ResponseEntity deleteRoutineStep(@PathVariable Long id) {
        routineService.deleteRoutineStep(id);
        return ResponseEntity.noContent().build();
    }
}
