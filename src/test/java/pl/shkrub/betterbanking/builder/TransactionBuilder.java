package pl.shkrub.betterbanking.builder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;

@Component
public class TransactionBuilder {

  private static final String WITHDRAW = "withdraw";
  private static final String DEPOSIT = "deposit";
  private static final String CURRENCY = "usd";
  private static final String MERCHANT_NAME = "shkrub";
  private static final String MERCHANT_LOGO = "shkrub";

  public List<Transaction> buildListWithAccountNumber(int accountNumber) {
    return List.of(
        Transaction.builder()
            .id(1)
            .accountNumber(accountNumber)
            .type(WITHDRAW)
            .date(LocalDateTime.now())
            .currency(CURRENCY)
            .merchantName(MERCHANT_NAME)
            .merchantLogo(MERCHANT_LOGO)
            .amount(100)
            .build(),
        Transaction.builder()
            .id(2)
            .accountNumber(accountNumber)
            .type(WITHDRAW)
            .date(LocalDateTime.now())
            .currency(CURRENCY)
            .merchantName(MERCHANT_NAME)
            .merchantLogo(MERCHANT_LOGO)
            .amount(200)
            .build(),
        Transaction.builder()
            .id(3)
            .accountNumber(accountNumber)
            .type(DEPOSIT)
            .date(LocalDateTime.now())
            .currency(CURRENCY)
            .merchantName(MERCHANT_NAME)
            .merchantLogo(MERCHANT_LOGO)
            .amount(300)
            .build()
    );
  }

  public List<TransactionDto> buildDtoListWithAccountNumber(int accountNumber) {
    return List.of(
        TransactionDto.builder()
            .accountNumber(accountNumber)
            .type(WITHDRAW)
            .date(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
            .currency(CURRENCY)
            .merchantName(MERCHANT_NAME)
            .merchantLogo(MERCHANT_LOGO)
            .amount(100)
            .build(),
        TransactionDto.builder()
            .accountNumber(accountNumber)
            .type(WITHDRAW)
            .date(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
            .currency(CURRENCY)
            .merchantName(MERCHANT_NAME)
            .merchantLogo(MERCHANT_LOGO)
            .amount(200)
            .build(),
        TransactionDto.builder()
            .accountNumber(accountNumber)
            .type(DEPOSIT)
            .date(LocalDateTime.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
            .currency(CURRENCY)
            .merchantName(MERCHANT_NAME)
            .merchantLogo(MERCHANT_LOGO)
            .amount(300)
            .build()
    );
  }

}
