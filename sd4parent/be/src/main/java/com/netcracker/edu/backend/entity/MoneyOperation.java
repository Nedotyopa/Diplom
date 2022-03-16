package com.netcracker.edu.backend.entity;

public class MoneyOperation {

    private Long id;
    private float amount;

    public MoneyOperation() {
    }

    public MoneyOperation(Long id, float amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
