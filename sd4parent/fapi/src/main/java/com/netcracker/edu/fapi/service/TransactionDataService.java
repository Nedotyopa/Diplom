package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.TransactionViewModel;

import java.util.List;

public interface TransactionDataService {

    List<TransactionViewModel> getAll();
    TransactionViewModel getTransactionById(Long id);
    TransactionViewModel saveTransaction(TransactionViewModel action);
    void deleteTransaction(Long id);
    List<TransactionViewModel> getTransactionsByLogin(String login);


}
