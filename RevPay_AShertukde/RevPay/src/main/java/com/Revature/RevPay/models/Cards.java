package com.Revature.RevPay.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Cards {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cards_id;
    @Column(nullable = false, unique = true)
    private String card_number;
    @Column(nullable = false)
    private String card_type;
    @Column(nullable = false)
    private String expiration_date;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "money_id")
    private Money money;
    public Cards(){ }

    public Cards(String card_number, String card_type, String expiration_date, Money money) {
        this.card_number = card_number;
        this.card_type = card_type;
        this.expiration_date = expiration_date;
        this.money = money;
    }

    public Cards(Integer cards_id, String card_number, String card_type, String expiration_date, Money money) {
        this.cards_id = cards_id;
        this.card_number = card_number;
        this.card_type = card_type;
        this.expiration_date = expiration_date;
        this.money = money;
    }

    public Integer getCards_id() {
        return cards_id;
    }

    public void setCards_id(Integer cards_id) {
        this.cards_id = cards_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cards cards = (Cards) o;
        return Objects.equals(cards_id, cards.cards_id) && Objects.equals(card_number, cards.card_number) && Objects.equals(card_type, cards.card_type) && Objects.equals(expiration_date, cards.expiration_date) && Objects.equals(money, cards.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cards_id, card_number, card_type, expiration_date, money);
    }

    @Override
    public String toString() {
        return "Cards{" +
                "cards_id=" + cards_id +
                ", card_number='" + card_number + '\'' +
                ", card_type='" + card_type + '\'' +
                ", expiration_date='" + expiration_date + '\'' +
                ", money=" + money +
                '}';
    }
}
