package online.beautyskin.beauty.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import online.beautyskin.beauty.entity.EmailDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@EnableAutoConfiguration
public class EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    public void sendMailTemplate(EmailDetails emailDetails) {
        try{
            Context context = new Context();
            context.setVariable("name", emailDetails.getFullName());
            context.setVariable("button", emailDetails.getButtonValue());
            context.setVariable("link", emailDetails.getLink());

            String text = templateEngine.process("emailtemplate", context);

            // create mail message
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            // setting mail details
            mimeMessageHelper.setFrom("daonqse182848@fpt.edu.vn");
            mimeMessageHelper.setTo(emailDetails.getReceiver());
            mimeMessageHelper.setText(text, true); // là 1 template html
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            mailSender.send(mimeMessage);


        } catch(MessagingException e) {
            e.printStackTrace();
        }
    }





}