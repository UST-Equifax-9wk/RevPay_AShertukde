package com.Revature.RevPay.repositories;

import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionsRepository extends JpaRepository<Transactions,Integer> {
    Set<Transactions> findAllBySender(Money sender);
    Set<Transactions> findAllByReceiver(Money receiver);
}
