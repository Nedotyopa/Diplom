package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.Transaction;
import com.netcracker.edu.backend.service.TransactionService;
import com.netcracker.edu.backend.repository.TransactionRepository;
import static com.netcracker.edu.backend.repository.specification.TransactionSpecification.transactionsFindByLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository repository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository repository) { this.repository = repository; }

    @Override
    public Transaction saveTransaction(Transaction action) {
        return repository.save(action);
    }

    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<Transaction> getTransactionsByLogin(String login) {
        return repository.findAll(transactionsFindByLogin(login));
    }

}
