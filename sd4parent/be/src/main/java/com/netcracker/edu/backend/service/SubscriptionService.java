package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.PackageSubscription;
import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.SubscribeCondition;
import com.netcracker.edu.backend.entity.SubscriptionRenewal;

import java.util.Optional;

public interface SubscriptionService {

    Subscription saveSubscription(Subscription sub);
    PackageSubscription savePackageSubscription(PackageSubscription sub);
    Optional<Subscription> getSubscriptionById(Long id);
    Iterable<Subscription> getAllSubscriptions();
    void deleteSubscription(Long id);
    Iterable<Subscription> getSubscriptionsByLogin(String login);
    SubscribeCondition computePrice(SubscribeCondition condition);
    void extendSubscription(SubscriptionRenewal sub);
}
