package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.TransactionViewModel;
import com.netcracker.edu.fapi.service.TransactionDataService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionDataServiceImpl implements TransactionDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<TransactionViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        TransactionViewModel[] transactionViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/transactions", TransactionViewModel[].class);
        return transactionViewModelResponse == null ? Collections.emptyList() : Arrays.asList(transactionViewModelResponse);
    }

    @Override
    public TransactionViewModel getTransactionById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/transactions/" + id, TransactionViewModel.class);
    }

    @Override
    public TransactionViewModel saveTransaction(TransactionViewModel action) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/transactions", action, TransactionViewModel.class).getBody();
    }

    @Override
    public void deleteTransaction(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/transactions/" + id);
    }

    @Override
    public List<TransactionViewModel> getTransactionsByLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        TransactionViewModel[] transactionViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/transactions/?login=" + login, TransactionViewModel[].class);
        return transactionViewModelResponse == null ? Collections.emptyList() : Arrays.asList(transactionViewModelResponse);
    }

}
