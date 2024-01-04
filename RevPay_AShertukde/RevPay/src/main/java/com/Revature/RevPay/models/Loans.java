package com.Revature.RevPay.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Loans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer loans_id;
    @Column(nullable = false)
    private Float rate;
    @Column(precision = 15, scale = 2, nullable = false)
    @Min(value = 0,message = "value must be greater then 0")
    private BigDecimal amount;
    @Column(nullable = false)
    private String start_date;
    @Column
    private String end_date;
    @Column(nullable = false)
    private Boolean isPaidback;
    @ManyToOne
    @JoinColumn(name = "receiver", nullable = false)
    private BusinessAccounts businessAccounts;

    public Loans() {
    }

    public Loans(Integer loans_id) {
        this.loans_id = loans_id;
    }

    public Loans(Integer loans_id, Float rate, BigDecimal amount, String start_date, String end_date, BusinessAccounts businessAccounts) {
        this.loans_id = loans_id;
        this.rate = rate;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.businessAccounts = businessAccounts;
    }

    public Loans(Float rate, BigDecimal amount, String start_date, String end_date, Boolean isPaidback) {
        this.rate = rate;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.isPaidback = isPaidback;
    }

    public Loans(Float rate, BigDecimal amount, String start_date, String end_date, BusinessAccounts businessAccounts) {
        this.rate = rate;
        this.amount = amount;
        this.start_date = start_date;
        this.end_date = end_date;
        this.businessAccounts = businessAccounts;
    }
    public Loans(Float rate, BigDecimal amount, String start_date, BusinessAccounts businessAccounts) {
        this.rate = rate;
        this.amount = amount;
        this.start_date = start_date;
        this.businessAccounts = businessAccounts;
    }
    public Loans(Float rate, BigDecimal amount, String start_date) {
        this.rate = rate;
        this.amount = amount;
        this.start_date = start_date;
    }

    public Boolean getPaidback() {
        return isPaidback;
    }

    public void setPaidback(Boolean paidback) {
        isPaidback = paidback;
    }

    public Integer getLoans_id() {
        return loans_id;
    }

    public void setLoans_id(Integer loans_id) {
        this.loans_id = loans_id;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
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
        Loans loans = (Loans) o;
        return Objects.equals(loans_id, loans.loans_id) && Objects.equals(rate, loans.rate) && Objects.equals(amount, loans.amount) && Objects.equals(start_date, loans.start_date) && Objects.equals(end_date, loans.end_date) && Objects.equals(businessAccounts, loans.businessAccounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loans_id, rate, amount, start_date, end_date, businessAccounts);
    }

    @Override
    public String toString() {
        return "Loans{" +
                "loans_id=" + loans_id +
                ", rate=" + rate +
                ", amount=" + amount +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", businessAccounts=" + businessAccounts +
                '}';
    }
}
