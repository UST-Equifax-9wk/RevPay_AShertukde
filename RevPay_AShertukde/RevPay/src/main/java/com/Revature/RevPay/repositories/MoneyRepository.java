package com.Revature.RevPay.repositories;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.models.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Integer> {
    Optional<Money> findByUserAccounts(UserAccounts userAccounts);
    Optional<Money> findByBusinessAccounts(BusinessAccounts businessAccounts);
}
