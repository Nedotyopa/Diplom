package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.MoneyOperation;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.repository.TransactionRepository;
import com.netcracker.edu.backend.service.WalletService;
import com.netcracker.edu.backend.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.netcracker.edu.backend.repository.specification.WalletSpecification.walletFindByLogin;

@Component
public class WalletServiceImpl implements WalletService {

    private WalletRepository repository;

    @Autowired
    public WalletServiceImpl(WalletRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public Wallet saveWallet(Wallet purse)
    {
        return repository.save(purse);
    }

    @Override
    public Optional<Wallet> getWalletById(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public Iterable<Wallet> getAllWallets()
    {
        return repository.findAll();
    }

    @Override
    public void deleteWallet(Long id)
    {
        repository.deleteById(id);
    }

    @Override
    public Optional<Wallet> getWalletByOwnerLogin (String login) {
        return repository.findOne(walletFindByLogin(login));
    }

    @Override
    public void fillUp(MoneyOperation purse) {
        repository.fillUp(purse.getAmount(), purse.getId());
    }

}
