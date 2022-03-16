package com.netcracker.edu.fapi.models;

public class DebtViewModel {

    private Long id;
    private float debt;

    public DebtViewModel() {
    }

    public DebtViewModel(Long id, float debt) {
        this.id = id;
        this.debt = debt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }
}
