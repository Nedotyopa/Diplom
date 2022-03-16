package com.netcracker.edu.fapi.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.netcracker.edu.fapi.config.JwtTokenUtil;
import com.netcracker.edu.fapi.models.AuthToken;
import com.netcracker.edu.fapi.models.LoginStringViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import com.netcracker.edu.fapi.service.EmailService;

@RestController
@RequestMapping("api")
public class PasswordController {

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private UserViewModel resetUser = null;

    @RequestMapping(value = "/forgot", method = RequestMethod.GET)
    public boolean sendEmail(@RequestParam("email") String email) {
        UserViewModel user = userDataService.getUserByEmail(email);
        if(user == null) {
            System.out.println("There are no email");
            return false;
        }
        else {
            user.setResetToken(UUID.randomUUID().toString());
            System.out.println("Token: " + user.getResetToken());

            LoginStringViewModel resetToken = new LoginStringViewModel(user.getLogin(), user.getResetToken());
            userDataService.updateToken(resetToken);

            emailService.sendEmail(emailService.createEmail(user));

            System.out.println("A password reset link has been sent to " + email);
            return true;
        }

    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public void resetPassword(@RequestParam("token") String token, HttpServletResponse httpServletResponse) {

        UserViewModel user = userDataService.getUserByResetToken(token);
        System.out.println("User: " + user.getLogin());

        resetUser = user;

        String redirectUrl = "http://localhost:4200/new-password";

        httpServletResponse.setHeader("Location", redirectUrl);
        httpServletResponse.setStatus(302);
    }

    @RequestMapping(value = "/reset-user", method = RequestMethod.GET)
    public ResponseEntity<?> getResetUser() {
        if(resetUser != null) {
            final String token = jwtTokenUtil.generateTokenForSignUp(resetUser.getLogin(), "RESET");
            resetUser = null;
            return ResponseEntity.ok(new AuthToken(token));
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.POST)
    public void updatePassword(@RequestBody LoginStringViewModel password) {
        userDataService.updatePassword(password);
    }

}
