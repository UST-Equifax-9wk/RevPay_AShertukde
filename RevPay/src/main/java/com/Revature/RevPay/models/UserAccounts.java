package com.Revature.RevPay.models;


import jakarta.persistence.*;

import java.util.Objects;

@Entity(name = "user_accounts")
public class UserAccounts {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userid;
    @Column(length = 45, nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String password;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    @Column(nullable = false)
    private String firstname;
    @Column(nullable = false)
    private String lastname;

    public UserAccounts(String username, String password, String email, String phone, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public UserAccounts()
    {

    }
    public UserAccounts(String username, String password, String email, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    public UserAccounts(String username) {
        this.username = username;
    }
    public UserAccounts(Integer userid) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccounts that = (UserAccounts) o;
        return Objects.equals(userid, that.userid) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userid, username, password, email, phone, firstname, lastname);
    }

    @Override
    public String toString() {
        return "UserAccounts{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
