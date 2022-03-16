package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonWriteNullProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonWriteNullProperties(false)
public class WalletViewModel {

    private Long id;
    private float money = 0;
    private Object owner;

    public WalletViewModel() {
    }

    public WalletViewModel(float money, Object owner) {
        this.money = money;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }
}
