package com.Revature.RevPay.services;

import com.Revature.RevPay.models.LoanPayments;
import com.Revature.RevPay.models.Loans;
import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.repositories.LoanPaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class LoanPaymentsService {
    private LoanPaymentsRepository loanPaymentsRepository;
    private MoneyService moneyService;
    private LoansService loansService;
    @Autowired
    public LoanPaymentsService(LoanPaymentsRepository loanPaymentsRepository,LoansService loansService,MoneyService moneyService)
    {
        this.loanPaymentsRepository = loanPaymentsRepository;
        this.loansService = loansService;
        this.moneyService = moneyService;
    }
    public LoanPayments save(LoanPayments loanpayments, Integer loanid) {
        Loans loans = loansService.get(loanid);
        if(loans.getPaidback())
        {
            return null;
        }
        loanpayments.setLoans(loans);
        Money money_business = moneyService.getBusinessBalanceByUsername(loans.getBusinessAccounts().getUsername());
        BigDecimal balance = money_business.getBalance();
        if(balance.compareTo(loanpayments.getPayment_amount()) >= 0)
        {
            BigDecimal sum = BigDecimal.valueOf(0);
            for(LoanPayments payments:this.get(loanid))
            {
                sum = sum.add(payments.getPayment_amount());
            }
            sum = sum.add(loanpayments.getPayment_amount());
            if(sum.compareTo(loans.getAmount()) <= 0)
            {
                money_business.setBalance(balance.subtract(loanpayments.getPayment_amount()));
                if(sum.compareTo(loans.getAmount()) == 0)
                {
                    loans.setPaidback(true);
                    loans.setEnd_date(loanpayments.getDate());
                    loansService.save(loans);
                }
            }
            else//the loan payments have exceeded the amount required so just make the loan payments add up to the loan amount minus the other payments instead
            {
                sum = sum.subtract(loanpayments.getPayment_amount());
                loanpayments.setPayment_amount(loans.getAmount().subtract(sum));
                money_business.setBalance(balance.subtract(loanpayments.getPayment_amount()));
                loans.setPaidback(true);
                loans.setEnd_date(loanpayments.getDate());
                loansService.save(loans);
            }
            moneyService.saveBusinessBalanceByUsername(money_business);
        }
        else{//can't pay the requested amount
            return null;
        }
        return loanPaymentsRepository.save(loanpayments);
    }
    public Set<LoanPayments> get(Integer loanid)
    {
        Loans loans = loansService.get(loanid);
        return loanPaymentsRepository.findAllByLoans(loans);
    }

    public List<LoanPayments> getAll() throws Exception{
        return loanPaymentsRepository.findAll();
    }
}
