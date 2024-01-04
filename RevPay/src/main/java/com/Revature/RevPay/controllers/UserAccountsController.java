package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.services.UserAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserAccountsController {
    private final UserAccountsService userAccountsService;
    @Autowired
    public UserAccountsController(UserAccountsService userAccountsService)
    {
        this.userAccountsService = userAccountsService;
    }
    //@CrossOrigin(origins="http://localhost:8080")
    @PostMapping(path = "/UserAccounts")
    @ResponseStatus(HttpStatus.OK)
    public UserAccounts RegisterUser(@RequestBody UserAccounts user){
        return userAccountsService.save(user);
    }
    //need to change to request a password and username
    @GetMapping(path = "/UserAccounts")
    @ResponseStatus(HttpStatus.OK)
    public UserAccounts GetUserById(@RequestParam(value = "id") Integer id)
    {
        return userAccountsService.get(id);
    }
}
