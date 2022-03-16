package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.DebtModel;
import com.netcracker.edu.backend.entity.LoginStringModel;
import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.getUserById(id);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<User> getAllUsers() { return userService.getAllUsers(); }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User saveUser(@RequestBody User account) {
        return userService.saveUser(account);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByLogin(@RequestParam("login") String login) {
        Optional<User> user = userService.getUserByLogin(login);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        return null;
    }

    @RequestMapping(value = "/ban", method = RequestMethod.GET)
    public void banUser(@RequestParam("id") Long id) {
        userService.banUser(id);
    }

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public void checkUser(@RequestParam("login") String login) {
        userService.checkUser(login);
    }

    @RequestMapping(value="/email", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByEmail(@RequestParam("email") String email) {
        Optional<User> user = userService.findUserByEmail(email);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());

        return null;
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public ResponseEntity<User> getUserByResetToken(@RequestParam("token") String resetToken) {
        Optional<User> user = userService.findUserByResetToken(resetToken);
        if(user.isPresent())
            return ResponseEntity.ok(user.get());
        else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/update-token", method = RequestMethod.POST)
    public void updateToken(@RequestBody LoginStringModel resetToken) {
        userService.updateToken(resetToken);
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.POST)
    public void updatePassword(@RequestBody LoginStringModel password) {
        userService.updatePassword(password);
    }

    @RequestMapping(value = "/update-debt", method = RequestMethod.POST)
    public void updateDebt(@RequestBody DebtModel debt) {
        userService.updateDebt(debt);
    }

}
