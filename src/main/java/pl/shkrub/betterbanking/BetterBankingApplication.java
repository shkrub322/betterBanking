package pl.shkrub.betterbanking;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import pl.shkrub.betterbanking.domain.data.Transaction;
import pl.shkrub.betterbanking.repository.TransactionRepository;

@SpringBootApplication
public class BetterBankingApplication {

  public static void main(String[] args) {
    SpringApplication.run(BetterBankingApplication.class, args);
  }

  @Bean
  @Profile("dev")
  public ApplicationRunner applicationRunner(TransactionRepository transactionRepository) {
    return args -> {
      transactionRepository.deleteAll();
      transactionRepository.saveAll(defaultTransactionList());
    };
  }

  private List<Transaction> defaultTransactionList() {
    return List.of(
        Transaction.builder()
            .currency("usd")
            .accountNumber(1)
            .amount(100)
            .date(LocalDateTime.now())
            .type("Credit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(1)
            .amount(200)
            .date(LocalDateTime.now())
            .type("Debit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(1)
            .amount(300)
            .date(LocalDateTime.now())
            .type("Credit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(2)
            .amount(50)
            .date(LocalDateTime.now())
            .type("Credit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(2)
            .amount(100)
            .date(LocalDateTime.now())
            .type("Debit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(3)
            .amount(100)
            .date(LocalDateTime.now())
            .type("Credit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(1)
            .amount(100)
            .date(LocalDateTime.now())
            .type("Debit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(1)
            .amount(500)
            .date(LocalDateTime.now())
            .type("Credit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(1)
            .amount(1000)
            .date(LocalDateTime.now())
            .type("Debit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build(),
        Transaction.builder()
            .currency("usd")
            .accountNumber(3)
            .amount(100)
            .date(LocalDateTime.now())
            .type("Credit")
            .merchantName("acme")
            .merchantLogo("acme-logo.png")
            .build()
        );
  }
}
