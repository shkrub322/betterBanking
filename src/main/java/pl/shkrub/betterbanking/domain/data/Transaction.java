package pl.shkrub.betterbanking.domain.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

  private String type;
  private long date;
  private String accountNumber;
  private String currency;
  private String merchantName;
  private int amount;
  private String merchantLogo;

}
