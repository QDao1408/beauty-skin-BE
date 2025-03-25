package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.UserRequest;
import online.beautyskin.beauty.entity.request.UserUpdateRequest;
import online.beautyskin.beauty.repository.UserRepository;
import online.beautyskin.beauty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class UserAPI {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity update(@PathVariable long id, @RequestBody UserUpdateRequest user) {
        User user1 = userService.update(user,id);
        return ResponseEntity.ok(user1);
    }

    @PutMapping("/lock/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity lockUser(@PathVariable long id) {
        User user = userService.lockUser(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/unlock/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity unLockUser(@PathVariable long id) {
        User user = userService.unLockUser(id);
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity delete(@PathVariable long id) {
        User u = userService.findById(id);
        userService.delete(u);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/inActive/{id}")
    public ResponseEntity inActive(@PathVariable long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create-staff")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity createStaff(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.createStaff(userRequest));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getById(@PathVariable long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

}
