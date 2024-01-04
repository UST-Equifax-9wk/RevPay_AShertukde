package com.Revature.RevPay.repositories;

import com.Revature.RevPay.models.LoanPayments;
import com.Revature.RevPay.models.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LoanPaymentsRepository extends JpaRepository<LoanPayments, Integer> {
    Set<LoanPayments> findAllByLoans(Loans loans);
}
