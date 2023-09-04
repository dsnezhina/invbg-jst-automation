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

    private String token;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @BeforeEach
    public void beforeEach() {
        //Obtain token
        Credentials credentials = new Credentials(TestData.validUsername, TestData.validPassword, TestData.personalDomain);
        LoginAPI loginAPI = new LoginAPI("");
        token = loginAPI.obtainToken(credentials);
    }

    @Test
    @DisplayName("Can create new item")
    @Tag("api")
    public void canCreateNewItem() {

        ItemAPI itemAPI = new ItemAPI(token);

        //Create item
        Item itemToCreate = new Item(TestData.itemName + LocalDateTime.now(), TestData.itemPrice, TestData.itemPriceForQuantity, TestData.itemQuantityUnit);

        //Check response status code
        Response createResponse = itemAPI.createItem(itemToCreate);
        Assertions.assertEquals(201, createResponse.statusCode());

        //Verify created item fields values match DTO values
        int itemId = createResponse.then().extract().jsonPath().getInt("id");
        Response getResponse = itemAPI.getItem(itemId);

        Item createdItem = gson.fromJson(getResponse.then().extract().body().asPrettyString(), Item.class);

        Assertions.assertEquals(itemToCreate.getName(), createdItem.getName());
        Assertions.assertEquals(itemToCreate.getPrice(), createdItem.getPrice());
        Assertions.assertEquals(itemToCreate.getPrice_for_quantity(), createdItem.getPrice_for_quantity());
        Assertions.assertEquals(itemToCreate.getQuantity_unit(), createdItem.getQuantity_unit());

        //TODO Compare both objects instead of field by field


    }

    @Test
    @DisplayName("Can get all items")
    @Tag("api")
    public void canGetAllItems() {

        ItemAPI itemAPI = new ItemAPI(token);

        //Check response status code
        Response getResponse = itemAPI.getAllItems();
        Assertions.assertEquals(200, getResponse.statusCode());
    }

    @Test
    @DisplayName("Can get single item")
    @Tag("api")
    public void canGetSingleItem() {

        ItemAPI itemAPI = new ItemAPI(token);

        //Create item
        Item itemToCreate = Item.builder()
                .name(TestData.itemName + LocalDateTime.now())
                .price(TestData.itemPrice)
                .price_for_quantity(TestData.itemPriceForQuantity)
                .quantity_unit(TestData.itemQuantityUnit)
                .build();

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

        ItemAPI itemAPI = new ItemAPI(token);

        //Create item
        Item itemToCreate = Item.builder()
                .name(TestData.itemName + LocalDateTime.now())
                .price(TestData.itemPrice)
                .price_for_quantity(TestData.itemPriceForQuantity)
                .quantity_unit(TestData.itemQuantityUnit)
                .build();

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

        ItemAPI itemAPI = new ItemAPI(token);

        //Create item
        Item itemToCreate = Item.builder()
                .name(TestData.itemName + LocalDateTime.now())
                .price(TestData.itemPrice)
                .price_for_quantity(TestData.itemPriceForQuantity)
                .quantity_unit(TestData.itemQuantityUnit)
                .build();
        Response createResp = itemAPI.createItem(itemToCreate);

        //Verify item is created
        Assertions.assertEquals(201, createResp.statusCode());

        //Get id of the newly created item
        int id = createResp.then().extract().jsonPath().getInt("id");

        //Update item's price
        double newPrice = itemToCreate.getPrice() + 1;
        itemToCreate.setPrice(newPrice);
        Response updateResp = itemAPI.updateItem(id, itemToCreate);

        //Verify item is updated
        Assertions.assertEquals(204, updateResp.statusCode()); //<- "error": "This method is not implemented yet."

        //Verify price is correctly updated
        Response getResp = itemAPI.getItem(id);
        double updatedItemPrice = getResp.path("price");
        Assertions.assertEquals(newPrice, updatedItemPrice);
    }
}
