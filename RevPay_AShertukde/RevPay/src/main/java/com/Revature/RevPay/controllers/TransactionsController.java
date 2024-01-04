package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.Transactions;
import com.Revature.RevPay.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class TransactionsController {
    private final TransactionsService transactionsService;
    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }
    @PostMapping(path = "/TransactionRequest")
   // @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Transactions makeRequest(@RequestBody Transactions transactions, @RequestParam Integer sender, @RequestParam Integer receiver)
    {
        return transactionsService.save(transactions,sender,receiver);
    }
    @PostMapping(path = "/TransactionSend")
    // @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Transactions sendMoney(@RequestBody Transactions transactions, @RequestParam Integer sender, @RequestParam Integer receiver)
    {
        return transactionsService.saveComplete(transactions,sender,receiver);
    }
    @PostMapping(path = "/TransactionResponse")
  //  @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Transactions respondRequest(@RequestBody Transactions transactions)
    {
        return transactionsService.update(transactions);
    }
    @GetMapping(path = "/GetAllRequests")
   // @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Set<Transactions> getAllRequests(@RequestParam Integer sender)
    {
        return transactionsService.getAllRequests(sender);
    }
    @GetMapping(path = "/GetAllRequestsToOthers")
    // @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.OK)
    public Set<Transactions> getAllRequestsByReceiver(@RequestParam Integer receiver)
    {
        return transactionsService.getAllRequestsToOthers(receiver);
    }
}
