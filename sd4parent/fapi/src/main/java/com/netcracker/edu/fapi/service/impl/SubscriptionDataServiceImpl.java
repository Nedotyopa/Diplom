package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.*;
import com.netcracker.edu.fapi.service.SubscriptionDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SubscriptionDataServiceImpl implements SubscriptionDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Autowired
    SubscriptionDataService subscriptionDataService;

    @Override
    public List<SubscriptionViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionViewModel[] subscriptionViewModelResponse = restTemplate.getForObject(backendServerUrl + "api/subscriptions", SubscriptionViewModel[].class);
        return subscriptionViewModelResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionViewModelResponse);
    }

    @Override
    public SubscriptionViewModel getSubscriptionById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "api/subscriptions/" + id, SubscriptionViewModel.class);
    }

    @Override
    public StringResponseViewModel saveSubscription(SubscriptionViewModel sub) {
        RestTemplate restTemplate = new RestTemplate();
        if(sub != null) {
            List<SubscriptionViewModel> subscriptions = subscriptionDataService.getSubscriptionsByLogin(sub.getUser().getLogin());
            List<String> posts = new ArrayList<>();

            for(SubscriptionViewModel subscription: subscriptions) {
                posts.add(subscription.getPost().getTitle());
                System.out.println("Post: " + subscription.getPost().getTitle());
            }

            if(!posts.contains(sub.getPost().getTitle())) {
                SubscriptionViewModel tempSub = restTemplate.postForEntity(backendServerUrl + "api/subscriptions", sub, SubscriptionViewModel.class).getBody();
                if(tempSub == null)
                    return new StringResponseViewModel("Not enough money");

                return new StringResponseViewModel("Successful");
            }
            else
                return new StringResponseViewModel("Already subscribed");
        }
        return new StringResponseViewModel("Empty subscription");
    }

    @Override
    public StringResponseViewModel savePackageSubscription(PackageSubscriptionViewModel sub) {
        RestTemplate restTemplate = new RestTemplate();
        String resultPosts = "";

        List<SubscriptionViewModel> subscriptions = subscriptionDataService.getSubscriptionsByLogin(sub.getSubscriptions().get(0).getUser().getLogin());
        List<String> posts = new ArrayList<>();

        for (SubscriptionViewModel subscript : subscriptions) {
            posts.add(subscript.getPost().getTitle());
        }

        for (SubscriptionViewModel subscription : sub.getSubscriptions()) {
            if (subscription != null) {
                if (posts.contains(subscription.getPost().getTitle())) {
                    resultPosts += subscription.getPost().getTitle() + ",";
                }
            }
        }
        if(resultPosts.length() > 0)
            return new StringResponseViewModel(resultPosts);
        else {
            PackageSubscriptionViewModel result = restTemplate.postForEntity(backendServerUrl + "api/subscriptions/package", sub, PackageSubscriptionViewModel.class).getBody();
            if (result == null)
                return new StringResponseViewModel("Not enough money");
            else
                return new StringResponseViewModel("Successful");
        }
    }


    @Override
    public void deleteSubscription(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "api/subscriptions/" + id);
    }

    @Override
    public List<SubscriptionViewModel> getSubscriptionsByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        SubscriptionViewModel[] subscriptionViewModelResponse = restTemplate.getForObject(backendServerUrl + "api/subscriptions/?login=" + login, SubscriptionViewModel[].class);
        return subscriptionViewModelResponse == null ? Collections.emptyList() : Arrays.asList(subscriptionViewModelResponse);
    }

    @Override
    public SubscribeConditionViewModel computePrice(SubscribeConditionViewModel condition) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "api/subscriptions/compute", condition, SubscribeConditionViewModel.class).getBody();
    }

    @Override
    public void extendSubscription(SubscriptionRenewalViewModel sub) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "api/subscriptions/extend", sub, SubscriptionRenewalViewModel.class).getBody();
    }

    @Override
    public void chargeMoney() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "api/subscriptions/charge", null , void.class);
    }

    @Override
    public int getCount(String login) {
        return getSubscriptionsByLogin(login).size();
    }
}
