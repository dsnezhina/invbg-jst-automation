package api;

import api.dto.Client;
import api.dto.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import ui.TestData;

public class ClientAPITests {

    private String token;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    public void beforeEach() {
        Credentials credentials = new Credentials(TestData.validUsername, TestData.validPassword, TestData.personalDomain);
        LoginAPI loginAPI = new LoginAPI("");
        token = loginAPI.obtainToken(credentials);
    }

    @Test
    @DisplayName("Can create new client")
    @Tag("api")
    public void canCreateNewClient() {

        ClientAPI clientAPI = new ClientAPI(token); //Obtain token

        Client client = new Client(TestData.clientName, TestData.clientTown, TestData.clientAddress, TestData.clientBulstat, TestData.clientMol); //Client DTO
        Response createResponse = clientAPI.createClient(client);  //Send request and save the response
        Assertions.assertEquals(201, createResponse.statusCode()); //Check status code


        int clientId = createResponse.then().extract().jsonPath().getInt("id"); //Extract saved client id
        Response getResponse = clientAPI.getClient(clientId); //Get client with id
        Client deserializedClient = gson.fromJson(getResponse.then().extract().body().asPrettyString(), Client.class); //Convert response body to DTO

        Assertions.assertEquals(client.getName(), deserializedClient.getName());
        Assertions.assertEquals(client.getTown(), deserializedClient.getTown());
        Assertions.assertEquals(client.getAddress(), deserializedClient.getAddress());
        Assertions.assertEquals(client.getBulstat(), deserializedClient.getBulstat());
        Assertions.assertEquals(client.getMol(), deserializedClient.getMol());

        clientAPI.deleteClient(clientId);

    }
}
