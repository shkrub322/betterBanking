package pl.shkrub.betterbanking.repository;

import static java.util.Map.entry;

import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class InMemoryMerchantDetailsRepository implements MerchantDetailsRepository {

  private final Map<String, String> nameToLogo = Map.ofEntries(
      entry("acme", "acme-logo.png")
  );

  @Override
  public String getLogoByName(String merchantName) {
    if (Objects.isNull(merchantName)) return "";
    return nameToLogo.getOrDefault(merchantName.toLowerCase(), "");
  }

}
