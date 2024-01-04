package com.Revature.RevPay.models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class BusinessAccounts {
    @Id
    @Column(name = "business_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer business_id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String password;
    @Column(nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private String business_name;
    @Column(nullable = false)
    private String email;
    public BusinessAccounts(){

    }

    public BusinessAccounts(String username) {
        this.username = username;
    }

    public BusinessAccounts(Integer business_id) {
        this.business_id = business_id;
    }

    public BusinessAccounts(Integer business_id, String username, String password, String phone, String business_name, String email) {
        this.business_id = business_id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.business_name = business_name;
        this.email = email;
    }
    public BusinessAccounts(String username, String password, String phone, String business_name, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.business_name = business_name;
        this.email = email;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessAccounts that = (BusinessAccounts) o;
        return Objects.equals(business_id, that.business_id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(phone, that.phone) && Objects.equals(business_name, that.business_name) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(business_id, username, password, phone, business_name, email);
    }

    @Override
    public String toString() {
        return "BusinessAccounts{" +
                "business_id=" + business_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", business_name='" + business_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
