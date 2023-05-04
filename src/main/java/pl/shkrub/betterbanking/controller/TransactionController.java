package pl.shkrub.betterbanking.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;
import pl.shkrub.betterbanking.mapper.TransactionMapper;
import pl.shkrub.betterbanking.service.TransactionService;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

  private final TransactionService transactionService;
  private final TransactionMapper transactionMapper;

  public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper) {
    this.transactionService = transactionService;
    this.transactionMapper = transactionMapper;
  }

  @GetMapping("{accountNumber}")
  private ResponseEntity<Collection<TransactionDto>> finaAllByAccountNumber(@PathVariable String accountNumber) {
    Collection<Transaction> transactions = transactionService.findAllByAccountNumber(accountNumber);
    Collection<TransactionDto> res = transactionMapper.fromData(transactions);
    return ResponseEntity.ok(res);
  }
}
