package com.netcracker.edu.fapi.service.impl;

import com.netcracker.edu.fapi.models.MoneyOperationViewModel;
import com.netcracker.edu.fapi.models.WalletViewModel;
import com.netcracker.edu.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class WalletDataServiceImpl implements WalletDataService {

    @Value("${backend.server.url}")
    private String backendServerUrl;

    @Override
    public List<WalletViewModel> getAll() {
        RestTemplate restTemplate = new RestTemplate();
        WalletViewModel[] walletViewModelResponse = restTemplate.getForObject(backendServerUrl + "/api/wallets", WalletViewModel[].class);
        return walletViewModelResponse == null ? Collections.emptyList() : Arrays.asList(walletViewModelResponse);
    }

    @Override
    public WalletViewModel getWalletById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/wallets/" + id, WalletViewModel.class);
    }

    @Override
    public WalletViewModel saveWallet(WalletViewModel purse) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(backendServerUrl + "/api/wallets", purse, WalletViewModel.class).getBody();
    }

    @Override
    public void deleteWallet(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(backendServerUrl + "/api/wallets/" + id);
    }

    @Override
    public WalletViewModel getWalletByOwnerLogin(String login) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(backendServerUrl + "/api/wallets/?login=" + login, WalletViewModel.class);
    }

    @Override
    public void setMoneyForWallet(MoneyOperationViewModel purse) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(backendServerUrl + "/api/wallets/fill", purse, MoneyOperationViewModel.class);
    }

}
