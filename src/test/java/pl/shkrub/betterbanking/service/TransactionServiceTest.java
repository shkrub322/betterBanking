package pl.shkrub.betterbanking.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.shkrub.betterbanking.BetterBankingApplication;

@SpringBootTest(classes = BetterBankingApplication.class)
class TransactionServiceTest {

  @Autowired
  private TransactionService transactionService;

  @Test
  public void itFindsAllProductsByAccountNumber() {
    final int accountNumber = 1;
    assertThat(transactionService.findAllByAccountNumber(accountNumber)).hasSize(3);
  }

}