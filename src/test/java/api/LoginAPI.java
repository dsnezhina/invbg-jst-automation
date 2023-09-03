package api;

import api.dto.Credentials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import ui.TestData;

public class LoginAPI {
    private static final String BASE_URL = "https://api.inv.bg";
    private static final String BASE_PATH = "v3";
    private static final String ENDPOINT = "/login/token";

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static String obtainToken(Credentials credentials) {

        String body = gson.toJson(credentials);

        return RestAssured
                .given()
                .baseUri(BASE_URL)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("User-Agent", "UA")
                .body(body)
                .when()
                .post(ENDPOINT)
                .prettyPeek()
                .then()
                .extract()
                .body().jsonPath().getString("token");
    }

}
