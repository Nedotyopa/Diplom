package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.*;
import com.netcracker.edu.backend.repository.TransactionRepository;
import com.netcracker.edu.backend.repository.WalletRepository;
import com.netcracker.edu.backend.service.SubscriptionService;
import com.netcracker.edu.backend.repository.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.netcracker.edu.backend.repository.specification.SubscriptionSpecification.subscriptionsFindByLogin;

@Component
public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionRepository repository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Subscription saveSubscription(Subscription sub) {

        sub.setDuration(convertDuration(sub.getDuration()));
        sub.setCost(sub.getCost()/sub.getDuration());

        if((sub.getUser().getWallet().getMoney() - sub.getCost()) < sub.getUser().getDebt() * (-1)) {
            System.out.println("Sub: null");
            return null;
        }

        this.transactionRepository.save(createTransaction(sub));
        this.transactionRepository.save(createAdminTransaction(sub));

        double tempCost = fixedNum(Double.valueOf(sub.getCost()));
        float cost = (float)tempCost;
        this.walletRepository.chargeMoney(cost, sub.getUser().getWallet().getId());
        this.walletRepository.fillUp(cost, Long.valueOf(1));
        System.out.println("Sub: true");
        sub.setStatus(true);
        return this.repository.save(sub);
    }

    @Override
    public PackageSubscription savePackageSubscription(PackageSubscription sub) {

        List<Subscription> subscripts = new ArrayList<>();

        float totalCost = 0;
        for(Subscription subscription: sub.getSubscriptions()) {
            subscription.setDuration(convertDuration(subscription.getDuration()));
            subscription.setCost(subscription.getCost()/subscription.getDuration());
            totalCost += subscription.getCost();
        }
        if(sub.getSubscriptions().get(0).getUser().getWallet().getMoney() - totalCost < sub.getSubscriptions().get(0).getUser().getDebt() * (-1))
            return null;

        for(Subscription subscription: sub.getSubscriptions()) {
            this.transactionRepository.save(createTransaction(subscription));
            this.transactionRepository.save(createAdminTransaction(subscription));

            double tempCost = fixedNum(Double.valueOf(subscription.getCost()));
            float cost = (float)tempCost;
            this.walletRepository.chargeMoney(cost, subscription.getUser().getWallet().getId());
            this.walletRepository.fillUp(cost, Long.valueOf(1));
            subscripts.add(this.repository.save(subscription));
        }

        return new PackageSubscription(subscripts);
    }

    private int convertDuration(int duration) {
        int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int currentMonth = Integer.parseInt(LocalDateTime.now().toString().split("-")[1]);
        int days = 0;

        int finalMonth = currentMonth + duration;

        if(finalMonth > 12) {
            finalMonth -= 12;
            for(int i=currentMonth; i<monthDays.length; i++) {
                days += monthDays[i-1];
            }

            for(int i=0; i<finalMonth; i++) {
                days += monthDays[i];
            }
        } else {
            for(int i=currentMonth; i<finalMonth; i++) {
                days += monthDays[i-1];
            }
        }

        return days;
    }

    @Override
    public Optional<Subscription> getSubscriptionById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public Iterable<Subscription> getAllSubscriptions() {
        return this.repository.findAll();
    }

    @Override
    public void deleteSubscription(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Iterable<Subscription> getSubscriptionsByLogin(String login) {
        return this.repository.findAll(subscriptionsFindByLogin(login));
    }

    @Override
    public SubscribeCondition computePrice(SubscribeCondition condition) {
        Double currentDiscount = 0.0;

        if(condition.getDuration() > 0 && condition.getDuration() < 4)
            currentDiscount = 0.0;
        if(condition.getDuration() > 3 && condition.getDuration() < 7)
            currentDiscount = condition.getDiscount()/3;
        if(condition.getDuration() > 6 && condition.getDuration() < 10)
            currentDiscount = condition.getDiscount()*2/3;
        if(condition.getDuration() > 9 && condition.getDuration() < 13)
            currentDiscount = condition.getDiscount();

        Double wholePrice = condition.getPrice() * condition.getDuration();
        Double discountPrice = condition.getPrice() * currentDiscount * condition.getDuration()/100;
        condition.setPrice(wholePrice - discountPrice);
        condition.setDiscount(currentDiscount);

        condition.setPrice(fixedNum(condition.getPrice()));
        condition.setDiscount(fixedNum(condition.getDiscount()));

        return condition;
    }

    private Double fixedNum(Double num) {
        int fix = num.toString().split("\\.")[1].length();
        String fixFormat;

        if(fix >= 2)
            fixFormat = ".##";
        else
            fixFormat = ".#";

        DecimalFormat df2 = new DecimalFormat(fixFormat);

        return Double.valueOf(df2.format(num));
    }

    @Override
    public void extendSubscription(SubscriptionRenewal sub) {
        int oldDuration = repository.findById(sub.getId()).get().getDuration();

        int newDuration = this.convertDuration(sub.getDuration());

        float newCost = sub.getCost()/(oldDuration + newDuration);

        repository.changeCost(sub.getId(), newCost);
        repository.extendSubscription(sub.getId(), newDuration);
    }

    private Transaction createTransaction(Subscription sub) {
        double tempCost = fixedNum(Double.valueOf(sub.getCost()));
        float cost = (float)tempCost;

        Transaction action = new Transaction();
        action.setAction("MINUS");
        action.setAmount(cost);
        action.setTitle(sub.getPost().getTitle());
        action.setWallet(sub.getUser().getWallet());

        return action;
    }

    private Transaction createAdminTransaction(Subscription sub) {
        double tempCost = fixedNum(Double.valueOf(sub.getCost()));
        float cost = (float)tempCost;

        Transaction action = new Transaction();
        action.setAction("PLUS");
        action.setAmount(cost);
        action.setTitle(sub.getPost().getTitle());
        action.setWallet(walletRepository.findById(Long.valueOf(1)).get());

        return action;
    }
}
