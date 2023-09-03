package api;

import api.dto.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ItemAPI {

    private static final String BASE_URL = "https://api.inv.bg";
    private static final String BASE_PATH = "v3";
    private static final String ENDPOINT = "/items";
    private String token;
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ItemAPI(String token) {
        this.token = token;
    }

    public RequestSpecification baseRequest() {

        return RestAssured
                .given()
                .log().all()
                .auth().oauth2(token)
                .baseUri(BASE_URL)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "UA");
    }

    public Response createItem(Item item) {

        String body = gson.toJson(item);

        return baseRequest()
                .body(body)
                .when()
                .post(ENDPOINT)
                .prettyPeek();
    }

    public Response deleteItem(int id) {

        return baseRequest()
                .when()
                .delete(ENDPOINT + "/" + id)
                .prettyPeek();
    }

    public Response updateItem(int id, Item item) {

        String body = gson.toJson(item);

        return baseRequest()
                .body(body)
                .when().patch(ENDPOINT + "/" + id)
                .prettyPeek();
    }

    public Response getItem(int id) {

        return baseRequest()
                .when()
                .get(ENDPOINT + "/" + id)
                .prettyPeek();
    }

    public Response getAllItems() {

        return baseRequest()
                .when()
                .get(ENDPOINT)
                .prettyPeek();
    }
}
