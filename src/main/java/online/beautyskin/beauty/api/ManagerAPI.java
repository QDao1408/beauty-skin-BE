package online.beautyskin.beauty.api;

import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.Manager;
import online.beautyskin.beauty.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerAPI {

    @Autowired
    ManagerService managerService;

    List<Manager> managers = new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getAllManager(){
        managers = managerService.getAllManager();
        return ResponseEntity.ok(managers);
    }

    @PostMapping("/create")
    public ResponseEntity createManager(@RequestBody@Valid Manager manager){
        managers.add(managerService.createManager(manager));
        return ResponseEntity.ok(manager);
    }

    @PutMapping("/update")
    public ResponseEntity updateManger(@PathVariable long id,@RequestBody@Valid Manager manager){
        managerService.updateManager(manager);
        return ResponseEntity.ok(manager);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteManager(@PathVariable long id){
        Manager manager = managerService.deleteManager(id);
        return ResponseEntity.ok(manager);
    }
}
