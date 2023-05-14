package pl.shkrub.betterbanking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
  private String type;
  private long date;
  private int accountNumber;
  private String currency;
  private String merchantName;
  private int amount;
  private String merchantLogo;
}
