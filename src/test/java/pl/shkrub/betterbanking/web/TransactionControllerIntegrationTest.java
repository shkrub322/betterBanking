package pl.shkrub.betterbanking.web;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import pl.shkrub.betterbanking.BetterBankingApplication;
import pl.shkrub.betterbanking.domain.dto.TransactionDto;
import pl.shkrub.betterbanking.service.TransactionService;

@SpringBootTest(classes = BetterBankingApplication.class)
@AutoConfigureMockMvc
public class TransactionControllerIntegrationTest {

  private static final String PATH = "/api/v1/transactions";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TransactionService transactionService;

  @Test
  public void itFindsAllByAccountNumber() throws Exception {
    final int accountNumber = 1;
    final String findAllByAccountNumber = PATH + "/%d";
    when(transactionService.findAllByAccountNumber(accountNumber))
            .thenReturn(List.of(new TransactionDto()));

    mockMvc.perform(get(String.format(findAllByAccountNumber, accountNumber)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(APPLICATION_JSON))
        .andExpect(jsonPath("$", hasSize(1)));
  }

}
