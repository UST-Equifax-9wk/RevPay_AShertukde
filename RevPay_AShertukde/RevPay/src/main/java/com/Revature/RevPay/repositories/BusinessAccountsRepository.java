package com.Revature.RevPay.repositories;

import com.Revature.RevPay.models.BusinessAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessAccountsRepository extends JpaRepository<BusinessAccounts,Integer> {
    Optional<BusinessAccounts> findByUsername(String username);
}
