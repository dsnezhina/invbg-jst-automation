package ui;

import org.apache.commons.lang3.RandomStringUtils;

public class TestData {
    public static String personalDomain = "sndim"; //Replace with your personal domain
    public static String validUsername = "dsnezhina@gmail.com"; //Replace with your username
    public static String validPassword = "q2w3e4"; //Replace with your password
    public static String invalidUsername = "JamesSmith2023@gmail.com";
    public static String invalidPassword = "pass123";

    public static String itemName = RandomStringUtils.randomAlphanumeric(3, 10);
    public static double itemPrice = Double.parseDouble(RandomStringUtils.randomNumeric(1,3));
    public static double itemPriceForQuantity = 1;
    public static String quantityUnit = "p";

}
