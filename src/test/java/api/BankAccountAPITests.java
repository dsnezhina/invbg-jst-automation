package api;

import api.dto.BankAccount;
import api.dto.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import ui.TestData;

public class BankAccountAPITests {
    private String token;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    public void beforeEach() {
        Credentials credentials = new Credentials(TestData.validUsername, TestData.validPassword, TestData.personalDomain);
        LoginAPI loginAPI = new LoginAPI("");
        token = loginAPI.obtainToken(credentials);
    }

    @Test
    @DisplayName("Can create new bank account")
    @Tag("api")
    public void canCreateNewBankAccount() {
        BankAccountAPI bankAccountAPI = new BankAccountAPI(token);

        BankAccount bankAccount = new BankAccount(TestData.bankName, TestData.bankIban, TestData.bankBic);
        Response createResponse = bankAccountAPI.createBankAccount(bankAccount);
        Assertions.assertEquals(201, createResponse.getStatusCode());
    }

    @Test
    @DisplayName("Can delete bank account")
    @Tag("api")
    @Disabled("Delete request sets obj property deleted to true, but doesn't remove the resource from the db. Deleted acc can be accessed with get request")
    public void canDeleteBankAccount() {
        BankAccountAPI bankAccountAPI = new BankAccountAPI(token);

        BankAccount bankAccount = new BankAccount(TestData.bankName, TestData.bankIban, TestData.bankBic);
        Response createResponse = bankAccountAPI.createBankAccount(bankAccount);
        Assertions.assertEquals(201, createResponse.getStatusCode());

        int bankAccountId = createResponse.then().extract().jsonPath().getInt("id");

        Response deleteResponse = bankAccountAPI.deleteBankAccount(bankAccountId);
        Assertions.assertEquals(204, deleteResponse.getStatusCode());

        Response getResponse = bankAccountAPI.getBankAccount(bankAccountId);
        Assertions.assertEquals(404, getResponse.getStatusCode()); //Actual is 200
    }
}
