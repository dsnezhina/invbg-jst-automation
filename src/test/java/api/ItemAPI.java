package api;

import api.dto.Item;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ItemAPI extends Request {

    private static final String ENDPOINT = "/items";

    public ItemAPI(String token) {
        super(token);
    }

    public Response createItem(Item item) {
        String body = GSON.toJson(item);
        return post(ENDPOINT, body);
    }

    public Response deleteItem(int id) {
        return delete(ENDPOINT + "/" + id);
    }

    public Response updateItem(int id, Item item) {
        String body = GSON.toJson(item);
        return patch(ENDPOINT + "/" + id, body);
    }

    public Response getItem(int id) {
        return get(ENDPOINT + "/" + id);
    }

    public Response getAllItems() {
        return get(ENDPOINT);
    }
}
