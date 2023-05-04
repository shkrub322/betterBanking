package pl.shkrub.betterbanking.service;

import java.util.Collection;
import java.util.Set;

import org.springframework.stereotype.Service;

import pl.shkrub.betterbanking.domain.data.Transaction;

@Service
public class TransactionService {

  public Collection<Transaction> findAllByAccountNumber(String accountNumber) {
    return Set.of(
        Transaction.builder().accountNumber(accountNumber).amount(1).currency("USD").build(),
        Transaction.builder().accountNumber(accountNumber).amount(2).currency("USD").build(),
        Transaction.builder().accountNumber(accountNumber).amount(3).currency("USD").build()
    );
  }

}
