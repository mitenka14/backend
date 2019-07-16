package kekstarter.services;

import kekstarter.models.Users;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String username;

    public Boolean isNull(Users user) {
        return StringUtils.isEmpty(user.getEmail());
    }

    public void send(String emailTo, String code) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject("Activation code");
        mailMessage.setText("Welcome to KEKstarter! Please, visit next link to finish registration: http://localhost:3000/auth/activation/"+code);
        mailSender.send(mailMessage);
    }
}