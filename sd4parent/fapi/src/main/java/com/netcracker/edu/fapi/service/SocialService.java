package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.SocialViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;

public interface SocialService {

    String createFacebookAuthorizationURL();
    SocialViewModel createFacebookAccessToken(String code);
    String createGoogleAuthorizationURL();
    SocialViewModel createGoogleAccessToken(String code);
    UserViewModel createUser(String login, String email);

}
