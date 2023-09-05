package ui;

import org.apache.commons.lang3.RandomStringUtils;

public class TestData {
    //Login
    public static String personalDomain = PersonalAccount.domain; //Replace with your personal domain
    public static String validUsername = PersonalAccount.username; //Replace with your username
    public static String validPassword = PersonalAccount.password; //Replace with your password
    public static String invalidUsername = "JamesSmith2023@gmail.com";
    public static String invalidPassword = "pass123";

    //Items
    public static String itemName = RandomStringUtils.randomAlphanumeric(3, 10);
    public static double itemPrice = Double.parseDouble(RandomStringUtils.randomNumeric(1, 3));
    public static double itemPriceForQuantity = 1;
    public static String itemQuantityUnit = "pcs.";

    //Clients
    public static String clientName = RandomStringUtils.randomAlphanumeric(3, 10);
    public static String clientTown = "Sofia";
    public static String clientAddress = "Vitosha 100";
    public static String clientBulstat = RandomStringUtils.randomNumeric(9);
    public static String clientMol = RandomStringUtils.randomAlphabetic(2, 8) + RandomStringUtils.randomAlphabetic(4, 10); //Георги Йорданов

    //Bank accounts
    public static String bankBank_en; //"Greengotts Bank"
    public static String bankAlias; //"Основен, в левове"
    public static String bankName = RandomStringUtils.randomAlphabetic(3, 10); //"Банка Грийнготс"
    public static String bankIban = RandomStringUtils.randomAlphanumeric(22); //"HP01GTSV945010BGN0AAN5"
    public static String bankBic = RandomStringUtils.randomAlphabetic(8);; //"GTSVLNDA"
    public static String bankCurrency; //"BGN"
    public static String bankAddress; //"Postfach 10 01 65 32547 Bad Oyenhausen"
    public static boolean bankIsDefault; //default -> false
}
