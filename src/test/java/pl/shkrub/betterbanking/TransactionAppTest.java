package pl.shkrub.betterbanking;


import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import pl.shkrub.betterbanking.controller.TransactionController;

@SpringBootTest(classes = BetterBankingApplication.class)
public class TransactionAppTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TransactionController transactionController;

  private static final String PATH = "/v1/transactions";

  @Test
  public void findAllByAccountNumber() {
    final int accountNumber = 1;
    final String findAllByAccountNumberPath = String.format(PATH + "/%d", accountNumber);

    JsonSchemaFactory jsonSchemaFactory =
        JsonSchemaFactory.newBuilder()
            .setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze())
            .freeze();

    var actual = RestAssuredMockMvc.given()
        .standaloneSetup(transactionController)
        .when()
        .get(String.format("http://localhost:%d/%s", port, findAllByAccountNumberPath))
        .then()
        .assertThat()
        .body(JsonSchemaValidator
            .matchesJsonSchemaInClasspath("transaction-schema.json")
            .using(jsonSchemaFactory))
        .assertThat()
        .status(HttpStatus.OK)
        .contentType(ContentType.JSON)
        .extract()
        .body()
        .as(List.class);

    assertThat(actual).hasSize(3);
  }

}
