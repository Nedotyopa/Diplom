package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Transaction;

import java.util.Optional;

public interface TransactionService {

    Transaction saveTransaction(Transaction action);
    Optional<Transaction> getTransactionById(Long id);
    Iterable<Transaction> getAllTransactions();
    void deleteTransaction(Long id);
    Iterable<Transaction> getTransactionsByLogin(String login);
}
