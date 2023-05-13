package pl.shkrub.betterbanking.adapter;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Component;

import com.acme.banking.model.OBCreditDebitCode1;
import com.acme.banking.model.OBCurrencyExchange5;
import com.acme.banking.model.OBMerchantDetails1;
import com.acme.banking.model.OBTransaction6;

import pl.shkrub.betterbanking.domain.data.Transaction;

@Component
public class OBTransactionAdapter {

  public Transaction adaptToTransaction(OBTransaction6 obTransaction) {
    String accountId = obTransaction.getAccountId();
    OBCreditDebitCode1 creditDebitIndicator = obTransaction.getCreditDebitIndicator();
    OBCurrencyExchange5 currencyExchange = obTransaction.getCurrencyExchange();
    OBMerchantDetails1 merchantDetails = obTransaction.getMerchantDetails();
    OffsetDateTime valueDateTime = obTransaction.getValueDateTime();
    boolean currencyExchangeIsNull = currencyExchange == null;

    return Transaction.builder()
        .accountNumber(accountId == null ? 0 : Integer.parseInt(accountId))
        .type(creditDebitIndicator == null ? "" : creditDebitIndicator.toString())
        .currency(currencyExchangeIsNull ? "" : currencyExchange.getUnitCurrency())
        .amount(currencyExchangeIsNull ? 0 : currencyExchange.getExchangeRate().intValue())
        .merchantName(merchantDetails == null ? "" : merchantDetails.getMerchantName())
        .date(valueDateTime == null ? null : valueDateTime.toLocalDateTime())
        .build();
  }

}
