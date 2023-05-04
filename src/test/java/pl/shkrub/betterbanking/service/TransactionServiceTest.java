package pl.shkrub.betterbanking.service;

import java.util.Collection;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import pl.shkrub.betterbanking.domain.data.Transaction;

class TransactionServiceTest {

  private final TransactionService transactionService = new TransactionService();

  @Test
  public void itFindsAllProductsByAccountNumber() {
    Collection<Transaction> actual = transactionService.findAllByAccountNumber("accountNumber");
    Assertions.assertThat(actual).hasSize(3);
  }

}