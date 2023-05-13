package pl.shkrub.betterbanking.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.vavr.control.Try;
import pl.shkrub.betterbanking.api.impl.RESTTransactionsAPIClient;
import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;
import pl.shkrub.betterbanking.mapper.TransactionMapper;
import pl.shkrub.betterbanking.repository.MerchantDetailsRepository;

@Service
public class TransactionService {

  private final RESTTransactionsAPIClient restTransactionsAPIClient;
  private final TransactionMapper transactionMapper;
  private final CircuitBreaker acmeCircuitBreaker;
  private final MerchantDetailsRepository merchantDetailsRepository;

  public TransactionService(RESTTransactionsAPIClient restTransactionsAPIClient,
                            TransactionMapper transactionMapper,
                            CircuitBreaker acmeCircuitBreaker,
                            MerchantDetailsRepository merchantDetailsRepository) {
    this.restTransactionsAPIClient = restTransactionsAPIClient;
    this.transactionMapper = transactionMapper;
    this.acmeCircuitBreaker = acmeCircuitBreaker;
    this.merchantDetailsRepository = merchantDetailsRepository;
  }

  public Collection<TransactionDto> findAllByAccountNumber(int accountNumber) {
    Callable<List<Transaction>> listCallable =
        acmeCircuitBreaker.decorateCallable(() -> restTransactionsAPIClient.findByAccountNumber(accountNumber));
    Try<List<Transaction>> result = Try.ofCallable(listCallable)
        .recover(throwable -> Collections.emptyList());
    List<Transaction> transactionList = result.get()
        .stream()
        .peek(transaction ->
            transaction.setMerchantLogo(merchantDetailsRepository.getLogoByName(transaction.getMerchantName())))
        .toList();
    return transactionMapper.fromData(transactionList);
  }

}
