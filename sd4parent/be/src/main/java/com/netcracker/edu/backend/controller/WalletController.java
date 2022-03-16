package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.MoneyOperation;
import com.netcracker.edu.backend.entity.Wallet;
import com.netcracker.edu.backend.service.WalletService;
import com.netcracker.edu.backend.service.impl.ChargeServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private ChargeServiceImpl chargeService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Wallet> getWalletById(@PathVariable(name = "id") Long id) {
        Optional<Wallet> wallet = walletService.getWalletById(id);
        if (wallet.isPresent())
            return ResponseEntity.ok(wallet.get());
        else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Wallet> getAllWallets() { return walletService.getAllWallets(); }

    @RequestMapping(method = RequestMethod.POST)
    public Wallet saveWallet(@RequestBody Wallet purse) { return walletService.saveWallet(purse); }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Wallet> deleteWallet(@PathVariable(name = "id") Long id) {
        walletService.deleteWallet(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Wallet> getWalletByOwnerLogin(@RequestParam("login") String login) {
        Optional<Wallet> wallet = walletService.getWalletByOwnerLogin(login);
        if (wallet.isPresent())
            return ResponseEntity.ok(wallet.get());
        else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/fill", method = RequestMethod.POST)
    public void fillUp(@RequestBody MoneyOperation purse) {
        walletService.fillUp(purse);
    }

}
