package online.beautyskin.beauty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetToken(String to, String token) {
        String resetUrl = "http://beautyskinshop.online/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset Request");
        message.setText("Click the link to reset your password: " + resetUrl);
        message.setFrom("daonqse182848@fpt.edu.vn");

        mailSender.send(message);
    }
}
