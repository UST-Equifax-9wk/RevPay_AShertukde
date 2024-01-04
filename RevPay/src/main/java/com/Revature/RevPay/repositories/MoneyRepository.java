package com.Revature.RevPay.repositories;

import com.Revature.RevPay.models.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<Money, Integer> {
}
