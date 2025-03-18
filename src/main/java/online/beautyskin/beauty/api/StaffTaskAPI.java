package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import online.beautyskin.beauty.service.StaffTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "api")
public class StaffTaskAPI {

    @Autowired
    private StaffTaskService staffTaskService;

    @GetMapping("/gets")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(staffTaskService.getAll());
    }

    @GetMapping("/get-by-current-staff")
    public ResponseEntity getByCurrentStaff() {
        return ResponseEntity.ok(staffTaskService.getByCurrentStaff());
    }

    @GetMapping("/get-by-staff/{id}")
    public ResponseEntity getByStaffId(@PathVariable long id) {
        return ResponseEntity.ok(staffTaskService.getByStaffId(id));
    }

    @PutMapping("/update-task/{id}")
    public ResponseEntity updateTaskForStaff(@PathVariable long taskId, @PathVariable long staffId) {
        return ResponseEntity.ok(staffTaskService.updateTaskForStaff(taskId,staffId));
    }


}
