package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${server.port}")
    private int serverPort;

    @Async
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }

    @Override
    public SimpleMailMessage createEmail(UserViewModel user) {
        String appUrl = "http://localhost:" + serverPort;

        SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
        passwordResetEmail.setFrom("venya.cracker@gmail.com");
        passwordResetEmail.setTo(user.getEmail());
        passwordResetEmail.setSubject("Password Reset Request");
        passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + "/api/reset?token=" + user.getResetToken());

        return passwordResetEmail;
    }
}
