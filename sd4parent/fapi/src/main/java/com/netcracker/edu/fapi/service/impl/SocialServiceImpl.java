package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.SocialViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.SocialService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class SocialServiceImpl implements SocialService {

    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.social.facebook.appId}")
    private String facebookAppId;
    @Value("${spring.social.facebook.appSecret}")
    private String facebookSecret;

    @Value("${spring.social.google.appId}")
    private String googleAppId;
    @Value("${spring.social.google.appSecret}")
    private String googleSecret;

    @Override
    public String createFacebookAuthorizationURL() {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:" + serverPort + "/social/facebook");
        params.setScope("email");
        System.out.println(oauthOperations.buildAuthorizeUrl(params));
        return oauthOperations.buildAuthorizeUrl(params);
    }

    @Override
    public String createGoogleAuthorizationURL() {
        GoogleConnectionFactory connectionFactory = new GoogleConnectionFactory(googleAppId, googleSecret);
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("http://localhost:" + serverPort + "/social/google");
        params.setScope("email");
        System.out.println(oauthOperations.buildAuthorizeUrl(params));
        return oauthOperations.buildAuthorizeUrl(params);
    }

    @Override
    public SocialViewModel createFacebookAccessToken(String code) {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:" + serverPort + "/social/facebook", null);
        return getFacebookInfo(accessGrant.getAccessToken());
    }

    @Override
    public SocialViewModel createGoogleAccessToken(String code) {
        GoogleConnectionFactory connectionFactory = new GoogleConnectionFactory(googleAppId, googleSecret);
        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:" + serverPort + "/social/google", null);
        return getGoogleInfo(accessGrant.getAccessToken());
    }

    private SocialViewModel getFacebookInfo(String accessToken) {
        Facebook facebook = new FacebookTemplate(accessToken);
        String field[] = {"email","name"};
        SocialViewModel model = new SocialViewModel();
        String userInfo = facebook.fetchObject("me", String.class, field);
        System.out.println(userInfo);
        String userEmail = userInfo.substring(10, 22) + "@gmail.com";
        String userLogin = userInfo.substring(47, 52) + "." + userInfo.substring(54, 60);
        model.setEmail(userEmail);
        model.setLogin(userLogin);

        return model;
    }

    private SocialViewModel getGoogleInfo(String accessToken) {
        Google google = new GoogleTemplate(accessToken);
        SocialViewModel model = new SocialViewModel();
        String[] names = google.userOperations().getUserInfo().getName().split(" ");
        String userLogin = names[0] + "." + names[1];
        model.setLogin(userLogin);
        model.setEmail(google.userOperations().getUserInfo().getEmail());
        return model;
    }

    @Override
    public UserViewModel createUser(String login, String email) {
        UserViewModel user = new UserViewModel();
        user.setLogin(login);
        user.setPassword(UUID.randomUUID().toString());
        user.setEmail(email);
        user.setRole("USER");
        return user;
    }

}
