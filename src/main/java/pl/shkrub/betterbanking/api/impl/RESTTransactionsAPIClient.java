package pl.shkrub.betterbanking.api.impl;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.acme.banking.model.OBTransaction6;

import pl.shkrub.betterbanking.adapter.acme.OBTransactionAdapter;
import pl.shkrub.betterbanking.api.TransactionApiClient;
import pl.shkrub.betterbanking.domain.data.Transaction;
import reactor.core.publisher.Flux;

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
        .uri(String.format("/accounts/%d/transactions", accountNumber))
        .exchangeToFlux(this::bodyToFlux)
        .toStream()
        .map(adapter::adaptToTransaction)
        .toList();
  }

  private Flux<OBTransaction6> bodyToFlux(ClientResponse clientResponse) {
    HttpStatusCode httpStatusCode = clientResponse.statusCode();
    if (httpStatusCode.isError()) {
      throw new RuntimeException(String.format("Error from acme api with status code %d", httpStatusCode.value()));
    }
    return clientResponse.bodyToFlux(OBTransaction6.class);
  }
}
