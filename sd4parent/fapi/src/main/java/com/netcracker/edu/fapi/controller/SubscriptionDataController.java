package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.*;
import com.netcracker.edu.fapi.service.SubscriptionDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/s")
public class SubscriptionDataController {

    @Autowired
    SubscriptionDataService subscriptionDataService;

    @RequestMapping
    public  ResponseEntity<List<SubscriptionViewModel>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<SubscriptionViewModel> getSubscriptionById(@PathVariable String id) {
        return ResponseEntity.ok(subscriptionDataService.getSubscriptionById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public StringResponseViewModel saveSubscription(@RequestBody SubscriptionViewModel sub) {
        return subscriptionDataService.saveSubscription(sub);
    }

    @RequestMapping(value = "/package", method = RequestMethod.POST)
    public StringResponseViewModel savePackageSubscription(@RequestBody PackageSubscriptionViewModel sub) {
        return subscriptionDataService.savePackageSubscription(sub);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteSubscription(@PathVariable String id) {
        subscriptionDataService.deleteSubscription(Long.valueOf(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<SubscriptionViewModel>> getSubscriptionsByLogin(@RequestParam("login") String login) {
        return ResponseEntity.ok(subscriptionDataService.getSubscriptionsByLogin(login));
    }

    @RequestMapping(value = "/compute", method = RequestMethod.POST)
    public ResponseEntity<SubscribeConditionViewModel> computePrice(@RequestBody SubscribeConditionViewModel condition) {
        if(condition != null) {
            return ResponseEntity.ok(subscriptionDataService.computePrice(condition));
        }
        return null;
    }

    @RequestMapping(value = "/extend", method = RequestMethod.POST)
    public void extendSubscription(@RequestBody SubscriptionRenewalViewModel sub) {
        if(sub != null) {
            subscriptionDataService.extendSubscription(sub);
        }
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public void chargeMoney() {
        subscriptionDataService.chargeMoney();
    }

    @RequestMapping(value = "/get-count", method = RequestMethod.GET)
    public int getCount(@RequestParam("login") String login) {
        return subscriptionDataService.getCount(login);
    }

}
