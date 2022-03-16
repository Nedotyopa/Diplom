package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.config.JwtTokenUtil;
import com.netcracker.edu.fapi.models.AuthToken;
import com.netcracker.edu.fapi.models.DebtViewModel;
import com.netcracker.edu.fapi.models.StringResponseViewModel;
import com.netcracker.edu.fapi.models.UserViewModel;
import com.netcracker.edu.fapi.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/u")
public class UserDataController {

    @Autowired
    UserDataService userDataService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserViewModel> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userDataService.getUserById(Long.valueOf(id)));
    }

    @RequestMapping
    public ResponseEntity<List<UserViewModel>> getAllUsers() { return ResponseEntity.ok(userDataService.getAll()); }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<UserViewModel> getUserByLogin(@RequestParam("login") String login) {
        return ResponseEntity.ok(userDataService.getUserByLogin(login));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserViewModel account) {
        if(account != null) {
            StringResponseViewModel response = userDataService.saveUser(account);

            String login = account.getLogin();
            if(login == null)
                login = account.getEmail();

            if(response.getStringResponse().equals("Successful registration")) {
                final String token = jwtTokenUtil.generateTokenForSignUp(login, "USER");
                return ResponseEntity.ok(new AuthToken(token));
            } else {
                return ResponseEntity.ok(new AuthToken(response.getStringResponse()));
            }
        }
        else
            return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String id) {
        userDataService.deleteUser(Long.valueOf(id));
    }

    @RequestMapping(value = "/ban", method = RequestMethod.GET)
    public void banUser(@RequestParam("id") String id) {
        userDataService.banUser(Long.valueOf(id));
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public void checkUser(@RequestParam("login") String login) {
        userDataService.checkUser(login);
    }

    @RequestMapping(value = "/update-debt", method = RequestMethod.POST)
    public void updateDebt(@RequestBody DebtViewModel debt) {
        userDataService.updateDebt(debt);
    }
}
