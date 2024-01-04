package com.Revature.RevPay.controllers;

import com.Revature.RevPay.models.Cards;
import com.Revature.RevPay.services.CardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200/", allowCredentials = "true")
public class CardsController {
    private CardsService cardsService;
    @Autowired
    public CardsController(CardsService cardsService)
    {
        this.cardsService = cardsService;
    }
    @PostMapping("/CardsUser")
    @ResponseStatus(HttpStatus.OK)
    public Cards addCard(@RequestBody Cards card, @RequestParam String username)
    {
        return cardsService.save(card,username);
    }
    @GetMapping("/CardsUser")
    @ResponseStatus(HttpStatus.OK)
    public Set<Cards> getCard(@RequestParam String username)
    {
        return cardsService.getCard(username);
    }
    @PostMapping("/CardsBusiness")
    @ResponseStatus(HttpStatus.OK)
    public Cards addBusinessCard(@RequestBody Cards card, @RequestParam String username)
    {
        return cardsService.saveBusiness(card,username);
    }
    @GetMapping("/CardsBusiness")
    @ResponseStatus(HttpStatus.OK)
    public Set<Cards> getBusinessCard(@RequestParam String username)
    {
        return cardsService.getBusinessCard(username);
    }
}
