package com.Revature.RevPay.services;

import com.Revature.RevPay.models.Money;
import com.Revature.RevPay.models.Transactions;
import com.Revature.RevPay.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TransactionsService {
    private MoneyService moneyService;
    private TransactionsRepository transactionsRepository;
    @Autowired
    public TransactionsService(MoneyService moneyService, TransactionsRepository transactionsRepository) {
        this.moneyService = moneyService;
        this.transactionsRepository = transactionsRepository;
    }

    public Transactions save(Transactions transactions, Integer sender, Integer receiver) {
        Money money_sender = moneyService.getBalanceById(sender);
        Money money_receiver = moneyService.getBalanceById(receiver);
        transactions.setReceiver(money_receiver);
        transactions.setSender(money_sender);
        return transactionsRepository.save(transactions);
    }
    public Transactions saveComplete(Transactions transactions, Integer sender, Integer receiver) {
        Money money_sender = moneyService.getBalanceById(sender);
        Money money_receiver = moneyService.getBalanceById(receiver);
        if(money_sender.getBalance().compareTo(transactions.getAmount()) >= 0)
        {
            money_sender.setBalance(money_sender.getBalance().subtract(transactions.getAmount()));
            money_receiver.setBalance(money_receiver.getBalance().add(transactions.getAmount()));
            transactions.setSender(moneyService.updateBalanceByMoney(money_sender));
            transactions.setReceiver(moneyService.updateBalanceByMoney(money_receiver));
            transactions.setIsComplete(true);
        }
        else{
            return null;
        }
        return transactionsRepository.save(transactions);
    }
    public Transactions update(Transactions transactions)
    {
        Transactions temptransaction = transactionsRepository.findById(transactions.getTransaction_id()).orElse(null);
        if(temptransaction == null)
        {
            return null;
        }
        Money money_sender = temptransaction.getSender();
        Money money_receiver = temptransaction.getReceiver();
        transactions.setAmount(temptransaction.getAmount());
        if(!temptransaction.getIsComplete())
        {
            if(money_sender.getBalance().compareTo(transactions.getAmount()) >= 0)
            {
                money_sender.setBalance(money_sender.getBalance().subtract(transactions.getAmount()));
                money_receiver.setBalance(money_receiver.getBalance().add(transactions.getAmount()));
                transactions.setSender(moneyService.updateBalanceByMoney(money_sender));
                transactions.setReceiver(moneyService.updateBalanceByMoney(money_receiver));
                transactions.setIsComplete(true);
            }
            else{
                transactions.setSender(money_sender);
                transactions.setReceiver(money_receiver);
            }
        }
        else{
            return null;
        }
        return transactionsRepository.save(transactions);
    }
    public Set<Transactions> getAllRequests(Integer sender) {
        Money money = moneyService.getBalanceById(sender);
        return transactionsRepository.findAllBySender(money);
    }
    public Set<Transactions> getAllRequestsToOthers(Integer receiver){
        Money money = moneyService.getBalanceById(receiver);
        return transactionsRepository.findAllByReceiver(money);
    }


}
