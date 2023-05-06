package pl.shkrub.betterbanking.web;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;

import org.junit.jupiter.api.Test;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class TransactionComponentTest {

  private static final String PATH = "/transactions";

  @Test
  public void findAllByAccountNumber() {
    final String accountNumber = "accountNumber";
    final String findAllByAccountNumberPath = PATH + "/%s";

    JsonSchemaFactory jsonSchemaFactory =
        JsonSchemaFactory.newBuilder()
            .setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze())
            .freeze();


    RestAssured
        .get(String.format(findAllByAccountNumberPath, accountNumber))
        .then()
        .assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("transaction-schema.json").using(jsonSchemaFactory));
  }

}
