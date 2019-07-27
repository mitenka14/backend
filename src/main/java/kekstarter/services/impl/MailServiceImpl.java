package kekstarter.services.impl;

import kekstarter.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;


    @Value("${spring.mail.username}")
    private String username;



    @Override
    public void send(String emailTo, String code) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject("Activation code");
        mailMessage.setText("Welcome to KEKstarter! Please, visit next link to finish registration: http://localhost:3000/users/activation/"+code);
        mailSender.send(mailMessage);
    }
}