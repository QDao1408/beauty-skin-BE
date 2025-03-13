package online.beautyskin.beauty.service;

import online.beautyskin.beauty.entity.PasswordResetToken;
import online.beautyskin.beauty.entity.User;
import online.beautyskin.beauty.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordResetTokenRepository resetTokenRepository;

    public void sendResetToken(User user, String token) {

        String mail = user.getMail();
        String userName = user.getFullName();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String resetUrl = "http://beautyskinshop.online/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mail);
        message.setSubject("Complete your password reset request");
        message.setText("Dear " + userName + "\n" +
                "You requested a password change at " + formattedDateTime + "\n" +
                "Click the link to reset your password: " + resetUrl + "\n" +
                "If you did not ask to reset your password you may want to review your recent account access for any unusual activity.");
        message.setFrom("daonqse182848@fpt.edu.vn");

        mailSender.send(message);
    }

    public void notifyPasswordChanged(User user) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getMail());
        message.setSubject("Complete your password reset request");
        message.setText("Dear " + user.getFullName() + "\n" +
                "You have successfully change your password at " + formattedDateTime);
        message.setFrom("daonqse182848@fpt.edu.vn");

        mailSender.send(message);

    }
}
