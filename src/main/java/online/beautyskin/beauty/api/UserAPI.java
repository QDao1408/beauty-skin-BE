package online.beautyskin.beauty.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import online.beautyskin.beauty.entity.PasswordResetToken;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.ChangePasswordRequest;
import online.beautyskin.beauty.entity.request.UserUpdateRequest;
import online.beautyskin.beauty.repository.PasswordResetTokenRepository;
import online.beautyskin.beauty.repository.UserRepository;
import online.beautyskin.beauty.service.PasswordResetTokenService;
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
    private PasswordResetTokenService resetTokenService;

    @Autowired
    private PasswordResetTokenRepository resetTokenRepository;

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

    @PutMapping("/changePassword/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public ResponseEntity changePassword(@PathVariable long id,@RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseEntity.ok(userService.changePassword(id, changePasswordRequest));
    }

    @PostMapping("/forgot-password")

    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        String rs = resetTokenService.sendResetPasswordEmail(email);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword, @RequestParam String confirmPassword) {
        String rs = userService.resetPassword(token, newPassword, confirmPassword);
        return ResponseEntity.ok(rs);
    }

    @PostMapping("/reset-password-2")
    public ResponseEntity<String> resetPassword2(@RequestParam String newPassword, @RequestParam String confirmPassword) {
        String token = "";
        String rs = userService.resetPassword(token, newPassword, confirmPassword);
        return ResponseEntity.ok(rs);
    }
//    @PostMapping("/forgetPassword")
//    @PreAuthorize("hasAnyAuthority('USER')")
//    public ResponseEntity forgetPassword(@RequestParam String mail) {
//        return ResponseEntity.ok(userService.forgetPassword(mail));
//
//    }

}
