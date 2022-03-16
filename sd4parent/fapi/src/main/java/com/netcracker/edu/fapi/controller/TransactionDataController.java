package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.TransactionViewModel;
import com.netcracker.edu.fapi.service.TransactionDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/t")
public class TransactionDataController {

    @Autowired
    TransactionDataService transactionDataService;

    @RequestMapping
    public ResponseEntity<List<TransactionViewModel>> getAllTransactions() {
        return ResponseEntity.ok(transactionDataService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TransactionViewModel> getTransactionById(@PathVariable String id) {
        return ResponseEntity.ok(transactionDataService.getTransactionById(Long.valueOf(id)));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TransactionViewModel> saveTransaction(@RequestBody TransactionViewModel action) {
        if(action != null)
            return ResponseEntity.ok(transactionDataService.saveTransaction(action));
        else
            return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTransaction(@PathVariable String id) {
        transactionDataService.deleteTransaction(Long.valueOf(id));
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<List<TransactionViewModel>> getTransactionsByLogin(@RequestParam("login") String login) {
        return ResponseEntity.ok(transactionDataService.getTransactionsByLogin(login));
    }

}
