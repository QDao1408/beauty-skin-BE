package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.PasswordResetToken;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.repository.PasswordResetTokenRepository;
import online.beautyskin.beauty.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PageableArgumentResolver pageableArgumentResolver;

    @Autowired
    private EmailService emailService;

    public void sendResetPasswordEmail(String email) {
        User user = userRepository.findByMail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Generate Token
        String token = UUID.randomUUID().toString();
        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiredTime(LocalDateTime.now().plusHours(1)); // Expires in 1 hour

        passwordResetTokenRepository.save(resetToken);

        // Send Email
        emailService.sendResetToken(user.getMail(), token);
    }

}
