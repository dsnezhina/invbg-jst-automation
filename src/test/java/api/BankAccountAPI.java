package api;

import api.dto.BankAccount;
import io.restassured.response.Response;

public class BankAccountAPI extends Request{
    private static final String ENDPOINT = "/bank/accounts";

    public BankAccountAPI(String token) {
        super(token);
    }

    public Response createBankAccount(BankAccount bankAccount) {
        String body = GSON.toJson(bankAccount);
        return post(ENDPOINT, body);
    }

    public Response deleteBankAccount(int id) {
        return delete(ENDPOINT + "/" + id);
    }

    public Response updateBankAccount(int id, BankAccount bankAccount) {
        String body = GSON.toJson(bankAccount);
        return patch(ENDPOINT + "/" + id, body);
    }

    public Response getBankAccount(int id) {
        return  get(ENDPOINT + "/" + id);
    }

    public Response getAllBankAccounts() {
        return  get(ENDPOINT);
    }

}
