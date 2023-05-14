package pl.shkrub.betterbanking.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import pl.shkrub.betterbanking.api.TransactionApiClient;
import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;
import pl.shkrub.betterbanking.mapper.TransactionMapper;
import pl.shkrub.betterbanking.repository.MerchantDetailsRepository;
import pl.shkrub.betterbanking.repository.TransactionRepository;

@Service
public class TransactionService {

  private static final Logger LOG = LoggerFactory.getLogger(TransactionService.class);

  private final TransactionApiClient restTransactionsAPIClient;
  private final TransactionMapper transactionMapper;
  private final MerchantDetailsRepository merchantDetailsRepository;
  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionApiClient restTransactionsAPIClient,
                            TransactionMapper transactionMapper,
                            MerchantDetailsRepository merchantDetailsRepository,
                            TransactionRepository transactionRepository) {
    this.restTransactionsAPIClient = restTransactionsAPIClient;
    this.transactionMapper = transactionMapper;
    this.merchantDetailsRepository = merchantDetailsRepository;
    this.transactionRepository = transactionRepository;
  }

  @CircuitBreaker(name = "transactionService", fallbackMethod = "findIntoDb")
  public Collection<TransactionDto> findAllByAccountNumber(int accountNumber) {
    List<Transaction> transactionList = restTransactionsAPIClient.findByAccountNumber(accountNumber)
        .stream()
        .peek(transaction ->
            transaction.setMerchantLogo(merchantDetailsRepository.getLogoByName(transaction.getMerchantName())))
        .toList();
    return transactionMapper.fromData(transactionList);
  }

  private List<Transaction> findIntoDb(int accountNumber, final Throwable throwable) {
    LOG.error("Error after acme api calling", throwable);
    return transactionRepository.findAllByAccountNumber(accountNumber);
  }

}
