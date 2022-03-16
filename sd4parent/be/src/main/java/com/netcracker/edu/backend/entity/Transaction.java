package com.netcracker.edu.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    private String action;
    private float amount;
    private String date;
    private String title;

    public Transaction() {
    }

    public Transaction(Wallet wallet, String action, float amount, String title) {
        this.wallet = wallet;
        this.action = action;
        this.amount = amount;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", wallet=" + wallet.toString() +
                ", action='" + action + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
