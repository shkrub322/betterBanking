package pl.shkrub.betterbanking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pl.shkrub.betterbanking.domain.data.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

  List<Transaction> findAllByAccountNumber(int accountNumber);

}
