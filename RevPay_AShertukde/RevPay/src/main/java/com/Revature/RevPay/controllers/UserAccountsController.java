package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.UserAccounts;
import com.Revature.RevPay.services.EmailService;
import com.Revature.RevPay.services.UserAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class UserAccountsController {
    private final EmailService emailService;
    private final UserAccountsService userAccountsService;
    @Autowired
    public UserAccountsController(EmailService emailService, UserAccountsService userAccountsService)
    {
        this.emailService = emailService;
        this.userAccountsService = userAccountsService;
    }
    @PostMapping(path = "/UserAccounts")
    @ResponseStatus(HttpStatus.OK)
    public UserAccounts RegisterUser(@RequestBody UserAccounts user){
        return userAccountsService.save(user);
    }
    @GetMapping(path = "/GetUserAccounts")
    @ResponseStatus(HttpStatus.OK)
    public UserAccounts GetUserById(@RequestParam(name = "email", required = false)String email,@RequestParam(name = "id", required = false) Integer id, @RequestParam(name = "username", required = false) String username)
    {
        UserAccounts result = null;
        if(id != null) {
            result = userAccountsService.get(id);
        }
        else if(username != null)
        {
            result = userAccountsService.getByUsername(username);
        }
        else if(email != null)
        {
            result = userAccountsService.getByEmail(email);
        }
        return result;
    }
    @PostMapping(path = "/email")
    @ResponseStatus(HttpStatus.OK)
    public String message(@RequestParam(name = "to") String to,@RequestParam(name = "info")String info)
    {
        this.emailService.sendMessage(to,"Forgot Password",info);
        return "Message Sent";
    }
}
