package com.Revature.RevPay.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.Objects;
@Entity(name = "transactions")
public class Transactions {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transaction_id;
    @Column(precision = 15, scale = 2, nullable = false)
    @Min(value = 0,message = "value must be greater then 0")
    private BigDecimal amount;
    @Column()
    private String date_sent;
    @Column(nullable = false)
    private Boolean isComplete;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receiver", nullable = false)
    private Money receiver;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender",nullable = false)
    private Money sender;

    public Transactions(){

    }

    public Transactions(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Transactions(Integer transaction_id,String date_sent, Boolean isComplete) {
        this.date_sent = date_sent;
        this.isComplete = isComplete;
        this.transaction_id = transaction_id;
    }

    public Transactions(String date_sent,Integer transaction_id) {
        this.date_sent = date_sent;
        this.transaction_id = transaction_id;
    }

    public Transactions(BigDecimal amount, String date_sent, Boolean isComplete, Money receiver, Money sender) {
        this.amount = amount;
        this.date_sent = date_sent;
        this.isComplete = isComplete;
        this.receiver = receiver;
        this.sender = sender;
    }

    public Transactions(Integer transaction_id, BigDecimal amount, String date_sent, Boolean isComplete, Money receiver, Money sender) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.date_sent = date_sent;
        this.isComplete = isComplete;
        this.receiver = receiver;
        this.sender = sender;
    }

    public Transactions(BigDecimal amount, String date_sent, Boolean isComplete) {
        this.amount = amount;
        this.date_sent = date_sent;
        this.isComplete = isComplete;
    }

    public Transactions(BigDecimal amount, String date_sent) {
        this.amount = amount;
        this.date_sent = date_sent;
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        this.transaction_id = transaction_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public void setDate_sent(String date_sent) {
        this.date_sent = date_sent;
    }

    public Boolean getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    public Money getReceiver() {
        return receiver;
    }

    public void setReceiver(Money receiver) {
        this.receiver = receiver;
    }

    public Money getSender() {
        return sender;
    }

    public void setSender(Money sender) {
        this.sender = sender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return Objects.equals(transaction_id, that.transaction_id) && Objects.equals(amount, that.amount) && Objects.equals(date_sent, that.date_sent) && Objects.equals(isComplete, that.isComplete) && Objects.equals(receiver, that.receiver) && Objects.equals(sender, that.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transaction_id, amount, date_sent, isComplete, receiver, sender);
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "transaction_id=" + transaction_id +
                ", amount=" + amount +
                ", date_sent='" + date_sent + '\'' +
                ", isComplete='" + isComplete + '\'' +
                ", receiver=" + receiver +
                ", sender=" + sender +
                '}';
    }
}
