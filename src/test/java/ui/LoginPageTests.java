package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

@Tag("login")
public class LoginPageTests {

    private WebDriver driver;
    private static final String BASE_URL = "https://" + TestData.personalDomain + ".inv.bg/";
    private static final long WAIT = 3;

    @BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));
        driver.manage().window().maximize();
        driver.get(BASE_URL);
        System.out.println("Test: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Tag("positive")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        //Navigate to login page
        WebElement headingElement = driver.findElement(By.cssSelector("h1"));
        Assertions.assertEquals("Вход в inv.bg", headingElement.getText());

        //Enter username
        WebElement usernameInputField = driver.findElement(By.id("loginusername"));
        usernameInputField.sendKeys(TestData.validUsername);
        System.out.println("Entering username: " + TestData.validUsername);

        //Enter password
        WebElement passwordInputField = driver.findElement(By.id("loginpassword"));
        passwordInputField.sendKeys(TestData.validPassword);
        System.out.println("Entering password: " + TestData.validPassword);

        //Click login button
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        System.out.println("Clicking login button");

        //Check that the user is logged in
        WebElement userPanel = driver.findElement(By.className("userpanel-header"));
        Assertions.assertEquals(TestData.validUsername, userPanel.getText());
    }

    @Test
    @Tag("negative")
    @DisplayName("Can't login with invalid credentials")
    public void cantLoginWithInvalidCredentials() {
        //Navigate to login page
        WebElement headingElement = driver.findElement(By.cssSelector("h1"));
        Assertions.assertEquals("Вход в inv.bg", headingElement.getText());

        //Enter username
        WebElement usernameInputField = driver.findElement(By.id("loginusername"));
        usernameInputField.sendKeys(TestData.invalidUsername);
        System.out.println("Entering username: " + TestData.invalidUsername);

        //Enter password
        WebElement passwordInputField = driver.findElement(By.id("loginpassword"));
        passwordInputField.sendKeys(TestData.invalidPassword);
        System.out.println("Entering password: " + TestData.invalidPassword);

        //Click login button
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        System.out.println("Clicking login button");

        //Check correct error message is displayed
        WebElement errorMessageBox = driver.findElement(By.id("error"));
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", errorMessageBox.getText().trim());
    }

    @Test
    @DisplayName("Can't login with blank credentials")
    @Tag("negative")
    public void cantLoginWithBlankCredentials() {
        //Verify navigation is successful
        WebElement headingElement = driver.findElement(By.cssSelector("h1"));
        Assertions.assertEquals("Вход в inv.bg", headingElement.getText());

        //Verify username and password fields are empty
        WebElement usernameInputField = driver.findElement(By.id("loginusername"));
        usernameInputField.clear();
        WebElement passwordInputField = driver.findElement(By.id("loginpassword"));
        passwordInputField.clear();

        //Click login button
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        System.out.println("Clicking login button");

        //Check correct error message is displayed
        WebElement errorMessageBox = driver.findElement(By.id("error"));
        Assertions.assertEquals("Моля, попълнете вашия email", errorMessageBox.getText().trim());
    }

    @Test
    @Tag("positive")
    @DisplayName("Can login and logout")
    public void canLoginAndLogout() {
        //Navigate to login page
        WebElement headingElement = driver.findElement(By.cssSelector("h1"));
        Assertions.assertEquals("Вход в inv.bg", headingElement.getText());

        //Enter username
        WebElement usernameInputField = driver.findElement(By.id("loginusername"));
        usernameInputField.sendKeys(TestData.validUsername);
        System.out.println("Entering username: " + TestData.validUsername);

        //Enter password
        WebElement passwordInputField = driver.findElement(By.id("loginpassword"));
        passwordInputField.sendKeys(TestData.validPassword);
        System.out.println("Entering password: " + TestData.validPassword);

        //Click login button
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        System.out.println("Clicking login button");

        //Check that the user is logged in
        WebElement userPanel = driver.findElement(By.className("userpanel-header"));
        Assertions.assertEquals(TestData.validUsername, userPanel.getText());

        //Logout
        userPanel.click();
        WebElement logoutButton = driver.findElement(By.className("selenium-button-logout"));
        logoutButton.click();
        System.out.println("Clicking logout button");

        //Check success message is displayed
        WebElement successMessageBox = driver.findElement(By.id("okmsg"));
        Assertions.assertEquals("Вие излязохте от акаунта си.", successMessageBox.getText().trim());

        //Check the redirect
        WebElement forgottenPasswordLink = driver.findElement(By.id("newpass2"));
        Assertions.assertTrue(forgottenPasswordLink.isDisplayed());
    }
}

