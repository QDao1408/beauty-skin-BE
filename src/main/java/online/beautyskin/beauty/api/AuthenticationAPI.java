package online.beautyskin.beauty.api;

import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.AuthenticationRequest;
import online.beautyskin.beauty.entity.request.UserRequest;
import online.beautyskin.beauty.entity.respone.AuthenticationResponse;
import online.beautyskin.beauty.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class AuthenticationAPI {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody UserRequest userRequest) {
        User user = authenticationService.register(userRequest);

        return ResponseEntity.ok(user);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/get")
    public ResponseEntity get() {
        List<User> users = authenticationService.getAllUsers();
        return ResponseEntity.ok(users);
    }


}
