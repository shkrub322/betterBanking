package pl.shkrub.betterbanking.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import pl.shkrub.betterbanking.builder.TransactionBuilder;
import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;
import pl.shkrub.betterbanking.mapper.TransactionMapper;
import pl.shkrub.betterbanking.repository.TransactionRepository;

class TransactionServiceTest {

  private final TransactionBuilder transactionBuilder = new TransactionBuilder();

  private final TransactionRepository transactionRepository = Mockito.mock(TransactionRepository.class);

  private final TransactionMapper transactionMapper = Mockito.mock(TransactionMapper.class);

  private final TransactionService transactionService = new TransactionService(transactionRepository, transactionMapper);

  @Test
  public void itFindsAllProductsByAccountNumber() {
    final int accountNumber = 1;
    List<Transaction> transactionSet = transactionBuilder.buildListWithAccountNumber(accountNumber);
    List<TransactionDto> expected = transactionBuilder.buildDtoListWithAccountNumber(accountNumber);

    when(transactionRepository.findAllByAccountNumber(accountNumber))
        .thenReturn(transactionSet);
    when(transactionMapper.fromData(transactionSet))
        .thenReturn(expected);

    Collection<TransactionDto> actual = transactionService.findAllByAccountNumber(accountNumber);

    assertThat(actual).isEqualTo(expected);
    verify(transactionRepository).findAllByAccountNumber(accountNumber);
    verify(transactionMapper).fromData(transactionSet);
  }

}