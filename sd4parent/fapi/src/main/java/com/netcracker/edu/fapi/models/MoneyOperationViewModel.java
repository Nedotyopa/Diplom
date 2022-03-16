package com.netcracker.edu.fapi.models;

public class MoneyOperationViewModel {

    private Long id;
    private float amount;

    public MoneyOperationViewModel() {
    }

    public MoneyOperationViewModel(Long id, float amount) {
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
