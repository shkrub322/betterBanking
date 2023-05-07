package pl.shkrub.betterbanking.mapper;

import java.time.ZoneOffset;
import java.util.Collection;

import org.springframework.stereotype.Component;

import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;

@Component
public class TransactionMapper {

  public Collection<TransactionDto> fromData(Collection<Transaction> transactions) {
    return transactions.stream()
        .map(this::fromData)
        .toList();
  }

  private TransactionDto fromData(Transaction transaction) {
    return TransactionDto.builder()
        .accountNumber(transaction.getAccountNumber())
        .type(transaction.getType())
        .date(transaction.getDate().atZone(ZoneOffset.UTC).toInstant().toEpochMilli())
        .merchantLogo(transaction.getMerchantLogo())
        .merchantName(transaction.getMerchantName())
        .amount(transaction.getAmount())
        .currency(transaction.getCurrency())
        .build();
  }

}
