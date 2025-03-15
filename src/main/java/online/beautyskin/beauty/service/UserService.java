package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.PasswordResetToken;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.entity.request.ChangePasswordRequest;
import online.beautyskin.beauty.entity.request.UserRequest;
import online.beautyskin.beauty.entity.request.UserUpdateRequest;
import online.beautyskin.beauty.repository.PasswordResetTokenRepository;
import online.beautyskin.beauty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordResetTokenRepository resetTokenRepository;

    @Autowired
    private EmailService emailService;

    public User update(UserUpdateRequest user, long id) {
        User newUser = userRepository.findById(id);
        newUser.setFullName(user.getFullName());
        newUser.setPhone(user.getPhone());
        newUser.setGender(user.getGender());
        newUser.setBirthday(user.getBirthday());
        return userRepository.save(newUser);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User delete(User user) {
        user.setDeleted(true);
        return userRepository.save(user);
    }

    public User inActiveUser(User user) {
        user.setActive(false);
        return userRepository.save(user);
    }

    public User findById(long id) {
        return userRepository.findById(id);
    }

    public String changePassword(Long id, ChangePasswordRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return "User not found!";
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return "New password and confirm password do not match";
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            return "Old password is incorrect!";
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return "Password changed successfully!";
    }

    public String resetPassword(String token, String newPassword, String confirmPassword) {
        PasswordResetToken resetToken = resetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));


        if (resetToken.isExpired()) {
            return ("Token has expired.");
        }

        if (!newPassword.equals(confirmPassword)) {
            return ("Confirm password not matched");
        }

        // Update Password
        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Delete the used token
        resetTokenRepository.delete(resetToken);


        emailService.notifyPasswordChanged(user);

        return ("Password reset successfully.");
    }

    public User lockUser(long id) {
        User user = userRepository.findById(id);
        user.setActive(false);
        return user;
    }

//    public String forgetPassword(String mail) {
//        Optional<User> optionalUser = userRepository.findByMail(mail);
//        if (optionalUser.isEmpty()) {
//            return "User not found!";
//        }
//        User user = optionalUser.get();
//
//        String newPassword = generatePassword();
//        user.setPassword(passwordEncoder.encode(newPassword));
//        userRepository.save(user);
//        return "Your password has been changed: "+newPassword;
//    }
//
//    private static final String SPECIAL_CHARACTERS = "@$#*!%&";
//    private static final SecureRandom RANDOM = new SecureRandom();
//    public static String generatePassword() {
//        String uuid = UUID.randomUUID().toString().replace("-", "").toUpperCase(); // Chuyển UUID thành chữ hoa
//        String randomSpecial = String.valueOf(SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length())));
//        String randomDigit = String.valueOf(RANDOM.nextInt(10)); // Chọn số từ 0-9
//        return uuid.substring(0, 8) + randomSpecial + randomDigit; // Đảm bảo đúng regex
//    }
}
