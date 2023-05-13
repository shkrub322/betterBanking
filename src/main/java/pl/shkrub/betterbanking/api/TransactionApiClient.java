package pl.shkrub.betterbanking.api;

import java.util.List;

import pl.shkrub.betterbanking.domain.data.Transaction;

public interface TransactionApiClient {

  List<Transaction> findByAccountNumber(int accountNumber);

}
