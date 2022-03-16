package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.MoneyOperationViewModel;
import com.netcracker.edu.fapi.models.WalletViewModel;
import com.netcracker.edu.fapi.service.WalletDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/w")
public class WalletDataController {

    @Autowired
    WalletDataService walletDataService;

    @RequestMapping
    public ResponseEntity<List<WalletViewModel>> getAllWallets() {
        return ResponseEntity.ok(walletDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<WalletViewModel> getWalletById(@PathVariable String id) {
        return ResponseEntity.ok(walletDataService.getWalletById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<WalletViewModel> saveWallet(@RequestBody WalletViewModel purse) {
        if(purse != null)
            return ResponseEntity.ok(walletDataService.saveWallet(purse));
        else
            return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<WalletViewModel> getWalletByOwnerLogin(@RequestParam("login") String login) {
        return ResponseEntity.ok(walletDataService.getWalletByOwnerLogin(login));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteWallet(@PathVariable String id) {
        walletDataService.deleteWallet(Long.valueOf(id));
    }

    @RequestMapping(value = "/fill", method = RequestMethod.POST)
    public void setMoneyForWallet(@RequestBody MoneyOperationViewModel purse) {
        walletDataService.setMoneyForWallet(purse);
    }

}
