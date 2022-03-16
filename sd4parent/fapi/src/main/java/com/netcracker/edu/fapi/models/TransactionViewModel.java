package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class TransactionViewModel {

    private long id;
    private String action;
    private WalletViewModel wallet;
    private float amount;
    private String date;
    private String title;

    public TransactionViewModel() {
    }

    public TransactionViewModel(long id, String action, WalletViewModel wallet, float amount, String date, String title) {
        this.id = id;
        this.action = action;
        this.wallet = wallet;
        this.amount = amount;
        this.date = date;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getAction() {
        return action;
    }

    public WalletViewModel getWallet() {
        return wallet;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setWallet(WalletViewModel wallet) {
        this.wallet = wallet;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
