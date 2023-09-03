package api;

import api.dto.Credentials;
import api.dto.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import ui.TestData;

import java.time.LocalDateTime;

public class ItemAPITests {

    @Test
    @DisplayName("Can create new item")
    @Tag("api")
    public void canCreateNewItem() {
        //Obtain token
        Credentials credentials = new Credentials(TestData.validUsername, TestData.validPassword, TestData.personalDomain);
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);

        //Create item
        Item itemToCreate = new Item();
        itemToCreate.name = TestData.itemName + LocalDateTime.now();
        itemToCreate.price = TestData.itemPrice;
        itemToCreate.price_for_quantity = TestData.itemPriceForQuantity;
        itemToCreate.quantity_unit = TestData.quantityUnit;

        //Check response status code
        Response createResponse = itemAPI.createItem(itemToCreate);
        Assertions.assertEquals(201, createResponse.statusCode());

        //Verify created item fields values match DTO values
        int itemId = createResponse.then().extract().jsonPath().getInt("id");
        Response getResponse = itemAPI.getItem(itemId);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Item createdItem = gson.fromJson(getResponse.then().extract().body().asPrettyString(), Item.class);

        Assertions.assertEquals(itemToCreate.name, createdItem.name);
        Assertions.assertEquals(itemToCreate.price, createdItem.price);
        Assertions.assertEquals(itemToCreate.price_for_quantity, createdItem.price_for_quantity);
        Assertions.assertEquals(itemToCreate.quantity_unit, createdItem.quantity_unit);

        //TODO Compare both objects instead of field by field


    }

    @Test
    @DisplayName("Can get all items")
    @Tag("api")
    public void canGetAllItems() {

        //Obtain token
        Credentials credentials = new Credentials(TestData.validUsername, TestData.validPassword, TestData.personalDomain);
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);

        //Check response status code
        Response getResponse = itemAPI.getAllItems();
        Assertions.assertEquals(200, getResponse.statusCode());
    }

    @Test
    @DisplayName("Can get single item")
    @Tag("api")
    public void canGetSingleItem() {

        //Obtain token
        Credentials credentials = new Credentials(TestData.validUsername, TestData.validPassword, TestData.personalDomain);
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);

        //Create item
        Item itemToCreate = new Item();
        itemToCreate.name = TestData.itemName + LocalDateTime.now();
        itemToCreate.price = TestData.itemPrice;
        itemToCreate.price_for_quantity = TestData.itemPriceForQuantity;
        itemToCreate.quantity_unit = TestData.quantityUnit;

        //Verify item is created
        Response createResponse = itemAPI.createItem(itemToCreate);
        Assertions.assertEquals(201, createResponse.statusCode());

        //Get item. Check status code
        int itemId = createResponse.then().extract().jsonPath().getInt("id");
        Response getResponse = itemAPI.getItem(itemId);
        Assertions.assertEquals(200, getResponse.statusCode());
    }

    @Test
    @DisplayName("Can get single item")
    @Tag("api")
    public void canDeleteSingleItem() {

        //Obtain token
        Credentials credentials = new Credentials(TestData.validUsername, TestData.validPassword, TestData.personalDomain);
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);

        //Create item
        Item itemToCreate = new Item();
        itemToCreate.name = TestData.itemName + LocalDateTime.now();
        itemToCreate.price = TestData.itemPrice;
        itemToCreate.price_for_quantity = TestData.itemPriceForQuantity;
        itemToCreate.quantity_unit = TestData.quantityUnit;

        //Verify item is created
        Response createResponse = itemAPI.createItem(itemToCreate);
        Assertions.assertEquals(201, createResponse.statusCode());

        //Send delete request and check status code
        int itemId = createResponse.then().extract().jsonPath().getInt("id");
        Response deleteResponse = itemAPI.deleteItem(itemId);
        Assertions.assertEquals(204, deleteResponse.statusCode());
    }

    @Test
    @Disabled("PATCH method is not implemented yet.")
    @Tag("api")
    @Tag("update")
    @DisplayName("Can update single item")
    public void canUpdateSingleItem() {

        //Obtain token
        Credentials credentials = new Credentials(TestData.validUsername, TestData.validPassword, TestData.personalDomain);
        String token = LoginAPI.obtainToken(credentials);
        ItemAPI itemAPI = new ItemAPI(token);

        //Create item
        Item itemToCreate = new Item();
        itemToCreate.name = TestData.itemName + LocalDateTime.now();
        itemToCreate.price = TestData.itemPrice;
        itemToCreate.price_for_quantity = TestData.itemPriceForQuantity;
        itemToCreate.quantity_unit = TestData.quantityUnit;
        Response createResp = itemAPI.createItem(itemToCreate);

        //Verify item is created
        Assertions.assertEquals(201, createResp.statusCode());

        //Get id of the newly created item
        int id = createResp.then().extract().jsonPath().getInt("id");

        //Update item's price
        double newPrice = itemToCreate.price + 1;
        itemToCreate.price = newPrice;
        Response updateResp = itemAPI.updateItem(id, itemToCreate);

        //Verify item is updated
        Assertions.assertEquals(204, updateResp.statusCode()); //<- "error": "This method is not implemented yet."

        //Verify price is correctly updated
        Response getResp = itemAPI.getItem(id);
        double updatedItemPrice = getResp.path("price");
        Assertions.assertEquals(newPrice, updatedItemPrice);


    }
}
