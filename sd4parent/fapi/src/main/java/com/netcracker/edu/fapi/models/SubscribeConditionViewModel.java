package com.netcracker.edu.fapi.models;

public class SubscribeConditionViewModel {

    private Double price;
    private Double discount;
    private int duration;

    public SubscribeConditionViewModel() {
    }

    public SubscribeConditionViewModel(Double price, Double discount, int duration) {
        this.price = price;
        this.discount = discount;
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
