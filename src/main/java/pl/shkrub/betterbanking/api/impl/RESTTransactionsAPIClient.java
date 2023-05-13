package pl.shkrub.betterbanking.api.impl;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.acme.banking.model.OBTransaction6;

import pl.shkrub.betterbanking.adapter.OBTransactionAdapter;
import pl.shkrub.betterbanking.api.TransactionApiClient;
import pl.shkrub.betterbanking.domain.data.Transaction;

@Component
public class RESTTransactionsAPIClient implements TransactionApiClient {

  private final WebClient webClient;
  private final OBTransactionAdapter adapter;

  public RESTTransactionsAPIClient(WebClient webClient, OBTransactionAdapter adapter) {
    this.webClient = webClient;
    this.adapter = adapter;
  }

  @Override
  public List<Transaction> findByAccountNumber(int accountNumber) {
    return webClient
        .get()
        .uri("/accounts/{accountId}/transactions")
        .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(OBTransaction6.class))
        .toStream()
        .map(adapter::adaptToTransaction)
        .toList();
  }
}
