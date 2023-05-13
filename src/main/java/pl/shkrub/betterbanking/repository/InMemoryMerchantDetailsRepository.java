package pl.shkrub.betterbanking.repository;

import static java.util.Map.entry;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class InMemoryMerchantDetailsRepository implements MerchantDetailsRepository {

  private final Map<String, String> nameToLogo = Map.ofEntries(
      entry("acme", "acme-logo.png")
  );

  @Override
  public String getLogoByName(String merchantName) {
    return nameToLogo.getOrDefault(merchantName.toLowerCase(), "");
  }

}
