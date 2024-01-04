package com.Revature.RevPay.repositories;

import com.Revature.RevPay.models.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountsRepository extends JpaRepository<UserAccounts, Integer> {
    Optional<UserAccounts> findByUsername(String username);
    Optional<UserAccounts> findByEmail(String email);
    Optional<UserAccounts> findByPhone(String phone);
}
