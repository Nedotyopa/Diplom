package com.netcracker.edu.backend.entity;

public class SubscribeCondition {

    private Double price;
    private Double discount;
    private int duration;

    public SubscribeCondition() {
    }

    public SubscribeCondition(double price, double discount, int duration) {
        this.price = price;
        this.discount = discount;
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
