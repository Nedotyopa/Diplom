package com.netcracker.edu.backend.entity;

import java.util.List;

public class PackageSubscription {

    private List<Subscription> subscriptions;

    public PackageSubscription() {
    }

    public PackageSubscription(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
