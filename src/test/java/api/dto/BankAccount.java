package api.dto;

import lombok.Getter;

@Getter
public class BankAccount {

    private String bank_en; //"Greengotts Bank"
    private String alias; //"Основен, в левове"
    private String bank; //"Банка Грийнготс"
    private String iban; //"HP01GTSV945010BGN0AAN5"
    private String bic; //"GTSVLNDA"
    private String currency; //"BGN"
    private String address; //"Postfach 10 01 65 32547 Bad Oyenhausen"
    private boolean isDefault; //default -> false

    public BankAccount(String bank, String iban, String bic) {
        this.bank = bank;
        this.iban = iban;
        this.bic = bic;
    }
}
