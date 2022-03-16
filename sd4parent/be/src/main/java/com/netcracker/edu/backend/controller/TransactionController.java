package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.Transaction;
import com.netcracker.edu.backend.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    TransactionController(TransactionService transactionService) { this.transactionService = transactionService; }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transaction> getTransactionById(@PathVariable(name = "id") Long id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        if(transaction.isPresent())
            return ResponseEntity.ok(transaction.get());
        else
            return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Transaction saveTransaction(@RequestBody Transaction action) {
        return transactionService.saveTransaction(action);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable(name = "id") Long id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public Iterable<Transaction> getTransactionsByLogin(@RequestParam("login") String login) {
        return transactionService.getTransactionsByLogin(login);
    }

}
