package pl.shkrub.betterbanking.mapper;

import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Objects;

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
    long date = Objects.isNull(transaction.getDate()) ? 0L :
        transaction.getDate().atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
    return TransactionDto.builder()
        .accountNumber(transaction.getAccountNumber())
        .type(transaction.getType())
        .date(date)
        .merchantLogo(transaction.getMerchantLogo())
        .merchantName(transaction.getMerchantName())
        .amount(transaction.getAmount())
        .currency(transaction.getCurrency())
        .build();
  }

}
