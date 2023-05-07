package pl.shkrub.betterbanking.controller;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.shkrub.betterbanking.domain.dto.TransactionDto;
import pl.shkrub.betterbanking.service.TransactionService;

@RequestMapping("/transactions")
@RestController
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping("{accountNumber}")
  private ResponseEntity<Collection<TransactionDto>> finaAllByAccountNumber(@PathVariable int accountNumber) {
    Collection<TransactionDto> transactions = transactionService.findAllByAccountNumber(accountNumber);
    return ResponseEntity.ok(transactions);
  }
}
