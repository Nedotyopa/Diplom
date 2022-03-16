package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.MoneyOperationViewModel;
import com.netcracker.edu.fapi.models.WalletViewModel;

import java.util.List;

public interface WalletDataService {

    List<WalletViewModel> getAll();
    WalletViewModel getWalletById(Long id);
    WalletViewModel saveWallet(WalletViewModel purse);
    void deleteWallet(Long id);
    WalletViewModel getWalletByOwnerLogin(String login);
    void setMoneyForWallet(MoneyOperationViewModel purse);

}
