package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.*;

import java.util.List;

public interface UserDataService {

    List<UserViewModel> getAll();
    UserViewModel getUserById(Long id);
    StringResponseViewModel saveUser(UserViewModel account);
    void deleteUser(Long id);
    UserViewModel getUserByLogin(String login);
    void banUser(Long id);
    void checkUser(String login);
    UserViewModel getUserByEmail(String email);
    UserViewModel getUserByResetToken(String resetToken);
    void updateToken(LoginStringViewModel resetToken);
    void updatePassword(LoginStringViewModel password);
    void updateDebt(DebtViewModel debt);
    boolean verifyUser(LoginUser user);
}
