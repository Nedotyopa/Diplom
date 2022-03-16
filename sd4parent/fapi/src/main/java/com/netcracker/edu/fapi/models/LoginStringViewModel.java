package com.netcracker.edu.fapi.models;

public class LoginStringViewModel {

    private String login;
    private String stringVariable;

    public LoginStringViewModel() {
    }

    public LoginStringViewModel(String login, String stringVariable) {
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
