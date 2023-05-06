package pl.shkrub.betterbanking.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import pl.shkrub.betterbanking.domain.data.Transaction;

class TransactionServiceTest {

  private final TransactionService transactionService = new TransactionService();

  @Test
  public void itFindsAllProductsByAccountNumber() {
    Collection<Transaction> actual = transactionService.findAllByAccountNumber("accountNumber");
    assertThat(actual).hasSize(3);
  }

}