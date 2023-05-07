package pl.shkrub.betterbanking.domain.data;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "transaction")
public class Transaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(nullable = false)
  private String type;
  private LocalDateTime date;
  @Column(nullable = false, name = "accountNumber")
  private int accountNumber;
  @Column(nullable = false)
  private String currency;
  @Column(name = "merchantName")
  private String merchantName;
  @Column(nullable = false)
  private int amount;
  @Column(name = "merchantLogo")
  private String merchantLogo;

}
