package com.Revature.RevPay.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Money {
    @Id
    @Column(name = "money_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer money_id;
    @Column(precision = 15, scale = 2, nullable = false)
    @Min(value = 0,message = "value must be greater then 0")
    private BigDecimal balance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = true)
    private UserAccounts userAccounts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "business_id", unique = true)
    private BusinessAccounts businessAccounts;
    public Money(BigDecimal balance, UserAccounts userAccounts) {
        this.balance = balance;
        this.userAccounts = userAccounts;
    }

    public Money(Integer money_id, BigDecimal balance, UserAccounts userAccounts, BusinessAccounts businessAccounts) {
        this.money_id = money_id;
        this.balance = balance;
        this.userAccounts = userAccounts;
        this.businessAccounts = businessAccounts;
    }
    public Money(Integer money_id, BigDecimal balance, UserAccounts userAccounts) {
        this.money_id = money_id;
        this.balance = balance;
        this.userAccounts = userAccounts;
    }
    public Money(Integer money_id, BigDecimal balance, BusinessAccounts businessAccounts) {
        this.money_id = money_id;
        this.balance = balance;
        this.businessAccounts = businessAccounts;
    }

    public Money() {

    }

    public Integer getMoney_id() {
        return money_id;
    }

    public void setMoney_id(Integer money_id) {
        this.money_id = money_id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public UserAccounts getUserAccounts() {
        return userAccounts;
    }

    public void setUserAccounts(UserAccounts userAccounts) {
        this.userAccounts = userAccounts;
    }

    public BusinessAccounts getBusinessAccounts() {
        return businessAccounts;
    }

    public void setBusinessAccounts(BusinessAccounts businessAccounts) {
        this.businessAccounts = businessAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(money_id, money.money_id) && Objects.equals(balance, money.balance) && Objects.equals(userAccounts, money.userAccounts) && Objects.equals(businessAccounts, money.businessAccounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money_id, balance, userAccounts, businessAccounts);
    }

    @Override
    public String toString() {
        return "Money{" +
                "money_id=" + money_id +
                ", balance=" + balance +
                ", userAccounts=" + userAccounts +
                ", businessAccounts=" + businessAccounts +
                '}';
    }
}
