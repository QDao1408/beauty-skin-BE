package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.*;
import online.beautyskin.beauty.entity.respone.AuthenticationResponse;
import online.beautyskin.beauty.entity.respone.StaffResponse;
import online.beautyskin.beauty.entity.respone.UserListResponse;
import online.beautyskin.beauty.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class AuthenticationAPI {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity register(@Valid @RequestBody UserRequest userRequest) throws SQLIntegrityConstraintViolationException {
        User user = authenticationService.register(userRequest);
        return ResponseEntity.ok(user);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity get() {
        List<UserListResponse> users = authenticationService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getStaff")
    @PreAuthorize("hasAnyAuthority('MANAGER')")
    public ResponseEntity getStaff() {
        List<StaffResponse> staffResponses = authenticationService.getAllStaff();
        return ResponseEntity.ok(staffResponses);
    }

    @PostMapping("forgot-password")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordRequest request) {
        authenticationService.forgotPassword(request);
        return ResponseEntity.ok("Forgot password successfully");
    }

    @PostMapping("reset-password")
    public ResponseEntity resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest) throws SQLIntegrityConstraintViolationException {
        authenticationService.resetPassword(resetPasswordRequest);
        return ResponseEntity.ok("Reset password successfully");
    }

    @PostMapping("/login-google")
    public ResponseEntity loginGoogle(@RequestBody LoginGoogleRequest googleRequest) {
        AuthenticationResponse authenticationResponse = authenticationService.loginGoogle(googleRequest);
        return ResponseEntity.ok(authenticationResponse);
    }



}
