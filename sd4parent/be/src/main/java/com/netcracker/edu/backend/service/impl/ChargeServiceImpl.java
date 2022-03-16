package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Subscription;
import com.netcracker.edu.backend.entity.Transaction;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.SubscriptionRepository;
import com.netcracker.edu.backend.repository.TransactionRepository;
import com.netcracker.edu.backend.service.ChargeService;
import com.netcracker.edu.backend.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.netcracker.edu.backend.repository.WalletRepository;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;

@Component
public class ChargeServiceImpl implements ChargeService {

    private ArrayList<Subscription> subscriptions = new ArrayList<>();

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void loadSubscriptions() {
        subscriptions.clear();
        Iterable<Subscription> iterableSubs = subscriptionRepository.findAll();
        for(Subscription sub: iterableSubs) {
            subscriptions.add(sub);
        }

    }

    private boolean checkMoney(Subscription sub) {
        boolean status;
        System.out.println("Current money: " + sub.getUser().getWallet().getMoney());
        if((sub.getUser().getWallet().getMoney() - sub.getCost()) <= (sub.getUser().getDebt() * (-1))) {
            status = false;
            subscriptionRepository.falseStatus(sub.getId());
        } else {
            status = true;
            subscriptionRepository.trueStatus(sub.getId());
        }

        return status;
    }

    @Override
    public void decreaseAmount() {

        for(Subscription sub: subscriptions) {
            if(checkMoney(sub)) {
                walletRepository.chargeMoney(fixedNum(sub.getCost()), sub.getUser().getWallet().getId());
                sub.getUser().getWallet().setMoney(sub.getUser().getWallet().getMoney() - fixedNum(sub.getCost()));
                walletRepository.fillUp(fixedNum(sub.getCost()), Long.valueOf(1));
                subscriptionRepository.decreaseDuration(sub.getId());
                sub.setDuration(sub.getDuration() - 1);
                saveIncreaseTransaction(fixedNum(sub.getCost()), sub.getPost().getTitle());
                System.out.println("Cost: " + sub.getCost());
                System.out.println("Money[" + sub.getUser().getWallet().getId() + "]: " + sub.getUser().getWallet().getMoney());
            }
        }
    }

    @Override
    @Scheduled(cron = "0 0 9 * * *")
    public void scheduledCharge() {
        loadSubscriptions();
//        decreaseDays();
        checkSubscription();
        decreaseAmount();
        saveDecreaseTransaction();
    }

    @Override
    public void decreaseDays() {
        for(Subscription sub: subscriptions) {
            if(checkMoney(sub)) {
                subscriptionRepository.decreaseDuration(sub.getId());
                sub.setDuration(sub.getDuration() - 1);
            }
        }
    }

    @Override
    public void checkSubscription() {
        ArrayList<Subscription> toDelete = new ArrayList<>();

        for(Subscription sub: subscriptions) {
            if(sub.getDuration() >= 0) {
                System.out.println("Left days[" + sub.getId() + "]: " + sub.getDuration());
            }
            else {
                toDelete.add(sub);
                subscriptionRepository.deleteById(sub.getId());
            }
        }

        for(Subscription deleteSub: toDelete) {
            subscriptions.remove(deleteSub);
        }
    }

    @Override
    public void saveDecreaseTransaction() {
        Transaction action;

        for(Subscription sub: subscriptions) {
            action = new Transaction(sub.getUser().getWallet(), "MINUS", fixedNum(sub.getCost()), sub.getPost().getTitle());
            transactionRepository.save(action);
        }
    }

    @Override
    public void saveIncreaseTransaction(float cost, String title) {
        Transaction action;
        action = new Transaction(walletRepository.findById(Long.valueOf(1)).get(), "PLUS", cost, title);
        transactionRepository.save(action);
    }

    private Float fixedNum(Float num) {
        int fix = num.toString().split("\\.")[1].length();
        String fixFormat;

        if(fix >= 2)
            fixFormat = ".##";
        else
            fixFormat = ".#";

        DecimalFormat df2 = new DecimalFormat(fixFormat);

        return Float.valueOf(df2.format(num));
    }
}
