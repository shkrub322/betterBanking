package pl.shkrub.betterbanking.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import pl.shkrub.betterbanking.api.impl.RESTTransactionsAPIClient;
import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;
import pl.shkrub.betterbanking.mapper.TransactionMapper;
import pl.shkrub.betterbanking.repository.MerchantDetailsRepository;

@Service
public class TransactionService {

  private final RESTTransactionsAPIClient restTransactionsAPIClient;
  private final TransactionMapper transactionMapper;
  private final MerchantDetailsRepository merchantDetailsRepository;

  public TransactionService(RESTTransactionsAPIClient restTransactionsAPIClient,
                            TransactionMapper transactionMapper,
                            MerchantDetailsRepository merchantDetailsRepository) {
    this.restTransactionsAPIClient = restTransactionsAPIClient;
    this.transactionMapper = transactionMapper;
    this.merchantDetailsRepository = merchantDetailsRepository;
  }

  @CircuitBreaker(name = "transactionService", fallbackMethod = "foundNone")
  public Collection<TransactionDto> findAllByAccountNumber(int accountNumber) {
    List<Transaction> transactionList = restTransactionsAPIClient.findByAccountNumber(accountNumber)
        .stream()
        .peek(transaction ->
            transaction.setMerchantLogo(merchantDetailsRepository.getLogoByName(transaction.getMerchantName())))
        .toList();
    return transactionMapper.fromData(transactionList);
  }

  private List<Transaction> foundNone(int accountNumber, final Throwable throwable) {
    return Collections.emptyList();
  }

}
