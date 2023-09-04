package api;

import api.dto.Client;
import io.restassured.response.Response;

public class ClientAPI extends Request{

    private static final String ENDPOINT = "/clients";

    public ClientAPI(String token) {
        super(token);
    }

    public Response createClient(Client client) {
        String body = GSON.toJson(client);
        return post(ENDPOINT, body);
    }

    public Response deleteClient(int id) {
        return delete(ENDPOINT + "/" + id);
    }

    public Response updateClient(int id, Client client) {
        String body = GSON.toJson(client);
        return patch(ENDPOINT + "/" + id, body);
    }

    public Response getClient(int id) {
        return  get(ENDPOINT + "/" + id);
    }

    public Response getAllClient() {
        return  get(ENDPOINT);
    }

}
