package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.config.JwtTokenUtil;
import com.netcracker.edu.fapi.models.AuthToken;
import com.netcracker.edu.fapi.models.SocialViewModel;
import com.netcracker.edu.fapi.models.URLViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.SocialService;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/social")
public class SocialController {

    private SocialViewModel socialModel = null;
    private String redirectUrl = "http://localhost:4200/get-social";

    @Autowired
    private SocialService socialService;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/createFacebookAuthorization")
    public URLViewModel createFacebookAuthorization() {
        return new URLViewModel(socialService.createFacebookAuthorizationURL());
    }

    @GetMapping("/facebook")
    public void createFacebookAccessToken(@RequestParam("code") String code, HttpServletResponse httpServletResponse) {

        socialModel = socialService.createFacebookAccessToken(code);
        System.out.println(socialModel.getEmail());
        System.out.println(socialModel.getLogin());

        httpServletResponse.setHeader("Location", redirectUrl);
        httpServletResponse.setStatus(302);
    }

    @GetMapping("/createGoogleAuthorization")
    public URLViewModel createGoogleAuthorization() {
        return new URLViewModel(socialService.createGoogleAuthorizationURL());
    }

    @GetMapping("/google")
    public void createGoogleAccessToken(@RequestParam("code") String code,  HttpServletResponse httpServletResponse) {

        socialModel = socialService.createGoogleAccessToken(code);

        System.out.println(socialModel.getEmail());
        System.out.println(socialModel.getLogin());

        httpServletResponse.setHeader("Location", redirectUrl);
        httpServletResponse.setStatus(302);
    }

    @GetMapping("/social-auth")
    public ResponseEntity<?> getSocialAuth() {
        String tokenLogin = "temp.user";
        String tokenRole = "temp.role";
        UserViewModel user;
        boolean check = true;
        Integer i = 0;

        if(socialModel != null) {
            user = userDataService.getUserByEmail(socialModel.getEmail());
            if(user != null) {
                tokenLogin = user.getLogin();
                tokenRole = user.getRole();
            } else {
              while(check) {
                  user = userDataService.getUserByLogin(socialModel.getLogin());
                  if(user == null) {
                      user = socialService.createUser(socialModel.getLogin(), socialModel.getEmail());
                      userDataService.saveUser(user);
                      tokenLogin = socialModel.getLogin();
                      tokenRole = "USER";
                      check = false;
                  } else {
                      i++;
                      socialModel.setLogin(socialModel.getLogin() + i.toString());
                  }
              }
            }
            final String token = jwtTokenUtil.generateTokenForSignUp(tokenLogin, tokenRole);
            socialModel = null;
            return ResponseEntity.ok(new AuthToken(token));
        } else {
            return null;
        }
    }

}
