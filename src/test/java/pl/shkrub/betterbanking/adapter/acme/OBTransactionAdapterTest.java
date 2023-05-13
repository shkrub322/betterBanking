package pl.shkrub.betterbanking.adapter.acme;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.acme.banking.model.OBCreditDebitCode1;
import com.acme.banking.model.OBCurrencyExchange5;
import com.acme.banking.model.OBMerchantDetails1;
import com.acme.banking.model.OBTransaction6;

import pl.shkrub.betterbanking.BetterBankingApplication;
import pl.shkrub.betterbanking.domain.data.Transaction;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = BetterBankingApplication.class)
class OBTransactionAdapterTest {

  @Autowired
  private OBTransactionAdapter adapter;

  @Test
  public void itAdapts() {
    LocalDateTime date = LocalDateTime.now();
    OBCurrencyExchange5 usd = new OBCurrencyExchange5()
        .exchangeRate(BigDecimal.valueOf(100))
        .unitCurrency("USD");
    OBMerchantDetails1 merchantName = new OBMerchantDetails1()
        .merchantName("merchantName");

    Transaction expected = Transaction.builder()
        .amount(100)
        .date(date)
        .merchantName("merchantName")
        .type("Credit")
        .accountNumber(1)
        .currency("USD")
        .build();
    OBTransaction6 obTransaction6 = new OBTransaction6()
        .currencyExchange(usd)
        .creditDebitIndicator(OBCreditDebitCode1.CREDIT)
        .accountId("1")
        .valueDateTime(date.atOffset(ZoneOffset.UTC))
        .merchantDetails(merchantName);

    Transaction actual = adapter.adaptToTransaction(obTransaction6);

    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void itAdaptsOnNullProperties() {
    Transaction expected = Transaction.builder()
        .amount(0)
        .date(null)
        .merchantName("")
        .type("")
        .accountNumber(0)
        .currency("")
        .build();

    OBTransaction6 obTransaction6 = new OBTransaction6()
        .currencyExchange(null)
        .creditDebitIndicator(null)
        .accountId(null)
        .valueDateTime(null)
        .merchantDetails(null);

    Transaction actual = adapter.adaptToTransaction(obTransaction6);

    assertThat(actual).isEqualTo(expected);
  }

}