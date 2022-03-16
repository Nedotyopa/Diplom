package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.*;

import java.util.List;

public interface SubscriptionDataService {

    List<SubscriptionViewModel> getAll();
    SubscriptionViewModel getSubscriptionById(Long id);
    StringResponseViewModel saveSubscription(SubscriptionViewModel sub);
    StringResponseViewModel savePackageSubscription(PackageSubscriptionViewModel sub);
    void deleteSubscription(Long id);
    List<SubscriptionViewModel> getSubscriptionsByLogin(String login);
    SubscribeConditionViewModel computePrice(SubscribeConditionViewModel condition);
    void extendSubscription(SubscriptionRenewalViewModel sub);
    void chargeMoney();
    int getCount(String login);
}
