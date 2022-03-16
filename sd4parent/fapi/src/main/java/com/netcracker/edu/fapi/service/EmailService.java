package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.UserViewModel;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage email);
    SimpleMailMessage createEmail(UserViewModel user);

}
