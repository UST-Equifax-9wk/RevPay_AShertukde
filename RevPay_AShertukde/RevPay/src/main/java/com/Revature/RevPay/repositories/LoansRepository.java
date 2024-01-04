package com.Revature.RevPay.repositories;

import com.Revature.RevPay.models.BusinessAccounts;
import com.Revature.RevPay.models.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface LoansRepository extends JpaRepository<Loans,Integer> {
    Set<Loans> findAllByBusinessAccounts(BusinessAccounts businessAccounts);
}
