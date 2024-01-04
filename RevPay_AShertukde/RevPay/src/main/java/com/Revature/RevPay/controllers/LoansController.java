package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.Loans;
import com.Revature.RevPay.services.LoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class LoansController {
    private LoansService loansService;
    @Autowired
    public LoansController(LoansService loansService)
    {
        this.loansService = loansService;
    }
    @PostMapping(path = "/BusinessLoan")
    @ResponseStatus(HttpStatus.OK)
    public Loans applyforloans(@RequestBody Loans loan, @RequestParam String username)
    {
        return loansService.save(loan,username);
    }
    @GetMapping(path = "/BusinessLoan")
    @ResponseStatus(HttpStatus.OK)
    public Set<Loans> getLoans(@RequestParam String username)
    {
        return loansService.get(username);
    }

}
