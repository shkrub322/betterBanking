package pl.shkrub.betterbanking.service;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;
import pl.shkrub.betterbanking.mapper.TransactionMapper;
import pl.shkrub.betterbanking.repository.TransactionRepository;

@Service
public class TransactionService {

  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;

  public TransactionService(TransactionRepository transactionRepository,
                            TransactionMapper transactionMapper) {
    this.transactionRepository = transactionRepository;
    this.transactionMapper = transactionMapper;
  }

  public Collection<TransactionDto> findAllByAccountNumber(int accountNumber) {
    List<Transaction> allByAccountNumber = transactionRepository.findAllByAccountNumber(accountNumber);
    return transactionMapper.fromData(allByAccountNumber);
  }

}
