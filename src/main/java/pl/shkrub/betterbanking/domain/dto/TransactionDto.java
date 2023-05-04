package pl.shkrub.betterbanking.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDto {
  private String type;
  private long date;
  private String accountNumber;
  private String currency;
  private String merchantName;
  private int amount;
  private String merchantLogo;
}
