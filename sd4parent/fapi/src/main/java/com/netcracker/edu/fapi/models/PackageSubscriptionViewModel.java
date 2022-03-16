package com.netcracker.edu.fapi.models;

import java.util.List;

public class PackageSubscriptionViewModel {

    private List<SubscriptionViewModel> subscriptions;

    public PackageSubscriptionViewModel() {
    }

    public PackageSubscriptionViewModel(List<SubscriptionViewModel> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<SubscriptionViewModel> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<SubscriptionViewModel> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
