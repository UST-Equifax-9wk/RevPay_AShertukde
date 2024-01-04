package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.services.BusinessAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class BusinessAccountsController {
    private final BusinessAccountsService businessAccountsService;
    @Autowired
    public BusinessAccountsController(BusinessAccountsService businessAccountsService)
    {
        this.businessAccountsService = businessAccountsService;
    }
    @PostMapping("/BusinessAccounts")
    @ResponseStatus(HttpStatus.OK)
    public BusinessAccounts saveBusinessaccount(@RequestBody BusinessAccounts businessAccounts)
    {
        return businessAccountsService.save(businessAccounts);
    }
    @GetMapping("/GetBusinessAccounts")
    @ResponseStatus(HttpStatus.OK)
    public BusinessAccounts getBusinessaccount(@RequestParam String username)
    {
        return businessAccountsService.getByUsername(username);
    }
}
