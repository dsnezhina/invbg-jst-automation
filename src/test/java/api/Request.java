package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Request {

    private String token;
    private String BASE_URL = "https://api.inv.bg";
    private String BASE_PATH = "v3";
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public Request(String token) {
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

    public Response get(String endpoint) {
        return baseRequest().get(endpoint).prettyPeek();
    }

    public Response delete(String endpoint) {
        return baseRequest().delete(endpoint).prettyPeek();
    }

    public Response post(String endpoint, String body) {
        return baseRequest().body(body).post(endpoint).prettyPeek();
    }

    public Response patch(String endpoint, String body) {
        return baseRequest().body(body).patch(endpoint).prettyPeek();
    }
}
