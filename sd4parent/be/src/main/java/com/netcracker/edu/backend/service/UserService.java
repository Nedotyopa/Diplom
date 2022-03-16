package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.DebtModel;
import com.netcracker.edu.backend.entity.LoginStringModel;
import com.netcracker.edu.backend.entity.User;

import java.util.Optional;

public interface UserService {

    User saveUser(User account);
    Optional<User>  getUserById(Long id);
    Iterable<User> getAllUsers();
    void deleteUser(Long id);
    Optional<User> getUserByLogin(String login);
    void banUser(Long id);
    void checkUser(String login);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByResetToken(String resetToken);
    void updateToken(LoginStringModel resetToken);
    void updatePassword(LoginStringModel password);
    void updateDebt(DebtModel debt);
}
