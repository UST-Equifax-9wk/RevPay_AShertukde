package com.Revature.RevPay.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class LoanPayments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer loanpayments_id;
    @Column(precision = 15, scale = 2, nullable = false)
    @Min(value = 0,message = "value must be greater then 0")
    private BigDecimal payment_amount;
    @Column(nullable = false)
    private String date;
    @JoinColumn(name = "loan_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Loans loans;

    public LoanPayments(BigDecimal payment_amount, String date, Loans loans) {
        this.payment_amount = payment_amount;
        this.date = date;
        this.loans = loans;
    }

    public LoanPayments(BigDecimal payment_amount, String date) {
        this.payment_amount = payment_amount;
        this.date = date;
    }

    public LoanPayments(Integer loanpayments_id, BigDecimal payment_amount, String date, Loans loans) {
        this.loanpayments_id = loanpayments_id;
        this.payment_amount = payment_amount;
        this.date = date;
        this.loans = loans;
    }

    public LoanPayments() {
    }

    public Integer getLoanpayments_id() {
        return loanpayments_id;
    }

    public void setLoanpayments_id(Integer loanpayments_id) {
        this.loanpayments_id = loanpayments_id;
    }

    public BigDecimal getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(BigDecimal payment_amount) {
        this.payment_amount = payment_amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Loans getLoans() {
        return loans;
    }

    public void setLoans(Loans loans) {
        this.loans = loans;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanPayments that = (LoanPayments) o;
        return Objects.equals(loanpayments_id, that.loanpayments_id) && Objects.equals(payment_amount, that.payment_amount) && Objects.equals(date, that.date) && Objects.equals(loans, that.loans);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanpayments_id, payment_amount, date, loans);
    }

    @Override
    public String toString() {
        return "LoanPayments{" +
                "loanpayments_id=" + loanpayments_id +
                ", payment_amount=" + payment_amount +
                ", date='" + date + '\'' +
                ", loans=" + loans +
                '}';
    }
}
