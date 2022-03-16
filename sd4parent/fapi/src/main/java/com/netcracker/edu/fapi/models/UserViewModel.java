package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)

public class UserViewModel {

    private long id;

    private String login;

    @NotNull
    private String password;
    private String role;

    @NotNull
    @Email
    private String email;
    private String lastDateLogin;
    private WalletViewModel wallet;
    private String resetToken;
    private float debt;
    private Boolean ban;
    private Integer count;

    public UserViewModel(String login, WalletViewModel wallet, String password, String role, String email, float debt, String lastDateLogin, Boolean ban, Integer count) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.email = email;
        this.lastDateLogin = lastDateLogin;
        this.wallet = wallet;
        this.debt = debt;
        this.ban = ban;
        this.count = count;
    }

    public UserViewModel(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public UserViewModel() {
        this.wallet = new WalletViewModel();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public WalletViewModel getWallet() {
        return wallet;
    }

    public void setWallet(WalletViewModel wallet) {
        this.wallet = wallet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastDateLogin() {
        return lastDateLogin;
    }

    public void setLastDateLogin(String lastDateLogin) {
        this.lastDateLogin = lastDateLogin;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public Boolean isBan() {
        return ban;
    }

    public void setBan(Boolean ban) {
        this.ban = ban;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
