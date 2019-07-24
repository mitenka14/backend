package kekstarter.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String username;



    public void send(String emailTo, String code) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject("Activation code");
        mailMessage.setText("Welcome to KEKstarter! Please, visit next link to finish registration: http://localhost:3000/auth/activation/"+code);
        mailSender.send(mailMessage);
    }
}