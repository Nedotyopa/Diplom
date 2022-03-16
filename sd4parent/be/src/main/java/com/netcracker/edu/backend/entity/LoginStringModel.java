package com.netcracker.edu.backend.entity;

public class LoginStringModel {

    private String login;
    private String stringVariable;

    public LoginStringModel() {
    }

    public LoginStringModel(String login, String stringVariable) {
        this.login = login;
        this.stringVariable = stringVariable;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getStringVariable() {
        return stringVariable;
    }

    public void setStringVariable(String stringVariable) {
        this.stringVariable = stringVariable;
    }
}
