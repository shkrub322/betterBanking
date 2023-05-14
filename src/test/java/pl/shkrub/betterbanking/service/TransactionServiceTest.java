package pl.shkrub.betterbanking.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import pl.shkrub.betterbanking.BetterBankingApplication;
import pl.shkrub.betterbanking.api.TransactionApiClient;
import pl.shkrub.betterbanking.domain.data.Transaction;

@SpringBootTest(classes = BetterBankingApplication.class)
class TransactionServiceTest {

  @Autowired
  @InjectMocks
  private TransactionService transactionService;

  @MockBean
  private TransactionApiClient transactionApiClient;

  @Test
  public void itFindsAllProductsByAccountNumber() {
    final int accountNumber = 1;
    when(transactionApiClient.findByAccountNumber(accountNumber))
            .thenReturn(List.of(new Transaction()));

    assertThat(transactionService.findAllByAccountNumber(accountNumber)).hasSize(1);
  }

}