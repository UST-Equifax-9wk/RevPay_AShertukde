package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.LoanPayments;
import com.Revature.RevPay.models.Loans;
import com.Revature.RevPay.services.LoanPaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class LoanPaymentsController {
    private LoanPaymentsService loanPaymentsService;
    @Autowired
    public LoanPaymentsController(LoanPaymentsService loanPaymentsService)
    {
        this.loanPaymentsService = loanPaymentsService;
    }
    @PostMapping(path = "/BusinessLoanPayment")
    @ResponseStatus(HttpStatus.OK)
    public LoanPayments applyforloans(@RequestBody LoanPayments loanpayments, @RequestParam Integer loanid)
    {
        return loanPaymentsService.save(loanpayments,loanid);
    }
    @GetMapping(path = "/BusinessLoanPayment")
    @ResponseStatus(HttpStatus.OK)
    public Set<LoanPayments> getLoanpayments(@RequestParam Integer loanid)
    {
        return loanPaymentsService.get(loanid);
    }
    @GetMapping(path = "/BusinessLoansAll")
    @ResponseStatus(HttpStatus.OK)
    public List<LoanPayments> getAllLoanPayments() throws Exception {return loanPaymentsService.getAll();}
}
