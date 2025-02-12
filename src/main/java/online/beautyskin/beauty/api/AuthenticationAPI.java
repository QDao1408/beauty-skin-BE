package online.beautyskin.beauty.api;

import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.UserRequest;
import online.beautyskin.beauty.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthenticationAPI {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody UserRequest userRequest) {
        User user = authenticationService.register(userRequest);

        return ResponseEntity.ok(user);
    }

    @PostMapping("login")
    public ResponseEntity login() {


        return null;
    }
}
