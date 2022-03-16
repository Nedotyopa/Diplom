package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.*;
import com.netcracker.edu.fapi.service.SubscriptionDataService;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("userDataService")
public class UserDataServiceImpl implements UserDataService, UserDetailsService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SubscriptionDataService subscriptionService;

    private static final String loginValidation = "^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
    private static final String passwordValidation = "^(?=.{8,}$)(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9._]+$";
    private static final String emailValidation = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";

    @Override
    public List<UserViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        UserViewModel[] userViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/users", UserViewModel[].class);
        for(UserViewModel userViewModel: userViewModelResponse) {
            userViewModel.setCount(this.subscriptionService.getCount(userViewModel.getLogin()));
        }
        return userViewModelResponse == null ? Collections.emptyList() : Arrays.asList(userViewModelResponse);
    }

    @Override
    public UserViewModel getUserById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/" + id, UserViewModel.class);
    }

    @Override
    public StringResponseViewModel saveUser(UserViewModel product) {

        if(product.getEmail() == null || product.getPassword() == null)
            return null;

        if(!product.getLogin().matches(loginValidation) || !product.getPassword().matches(passwordValidation) || !product.getEmail().matches(emailValidation)) {
            System.out.println("Validation false");
            return null;
        }

        product.setPassword(passwordEncoder.encode(product.getPassword()));

        UserViewModel loginModel = getUserByLogin(product.getLogin());
        UserViewModel emailModel = getUserByEmail(product.getEmail());

        if(loginModel != null)
            return new StringResponseViewModel("This login already exists");
        if(emailModel != null)
            return new StringResponseViewModel("This email already exists");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/users/login", product, UserViewModel.class).getBody();
        return new StringResponseViewModel("Successful registration");
    }

    @Override
    public void deleteUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/users/" + id);
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserViewModel user = getUserByLogin(name);
        if (user == null) {
            return null;
//            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }

    @Override
    public UserViewModel getUserByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/?login=" + login, UserViewModel.class);
    }

    private Set<GrantedAuthority> getAuthority(UserViewModel user) {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        authorities.add(new SimpleGrantedAuthority(user.isBan().toString()));
        return authorities;
    }

    @Override
    public void banUser(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(backendServerUrl + "/api/users/ban?id=" + id, void.class);
    }

    @Override
    public void checkUser(String login) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(backendServerUrl + "/api/users/check?login=" + login, void.class);
    }

    @Override
    public UserViewModel getUserByEmail(String email) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/email?email=" + email, UserViewModel.class);
    }

    @Override
    public UserViewModel getUserByResetToken(String resetToken) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/users/reset?token=" + resetToken, UserViewModel.class);
    }

    @Override
    public void updateToken(LoginStringViewModel resetToken) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/users/update-token", resetToken, LoginStringViewModel.class).getBody();
    }

    @Override
    public void updatePassword(LoginStringViewModel password) {
        password.setStringVariable(passwordEncoder.encode(password.getStringVariable()));

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/users/update-password", password, LoginStringViewModel.class).getBody();
    }

    @Override
    public void updateDebt(DebtViewModel debt) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/users/update-debt", debt, DebtViewModel.class).getBody();
    }

    @Override
    public boolean verifyUser(LoginUser user) {

        if(user.getLogin() == null || user.getPassword() == null)
            return false;

        if(!user.getLogin().matches(loginValidation) || !user.getPassword().matches(passwordValidation)) {
            System.out.println("Validation false");
            return false;
        }
        else {
            UserViewModel currentUser = getUserByLogin(user.getLogin());
            if(currentUser != null) {
                if(passwordEncoder.matches(user.getPassword(), currentUser.getPassword())) {
                    return true;
                }
                else {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

}
