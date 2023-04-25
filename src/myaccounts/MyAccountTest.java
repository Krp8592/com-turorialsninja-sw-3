package myaccounts;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

import java.util.UUID;

public class MyAccountTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    /**
     * Create method with name "selectMenu" it has one parameter name "menu" of type string
     * This method should click on the menu whatever name is passed as parameter
     */
    public void selectMyAccountOptions(String option) throws InterruptedException {
        //Generic X-path for Options navigation
        Thread.sleep(1000);
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        clickOnElement(By.xpath("(//a[normalize-space()='" + option + "'])[1]"));
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @Test
    public void verifyUserShouldNavigateToRegisterPageSuccessfully() throws InterruptedException {

        //Click on My Account Link
        selectMyAccountOptions("Register");
        Thread.sleep(1000);

        /**
         * Verify the text “Register Account
         */
        String expectedMsg = "Register Account";
        String actualMsg = getTextFromElement(By.xpath("//h1[normalize-space()='Register Account']"));
        verifyText("The expected text is not displayed", expectedMsg, actualMsg);
    }

    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully() throws InterruptedException {

        //Click on My Account Link
        selectMyAccountOptions("Login");

        /**
         *  Verify the text “Returning Customer”.
         */
        String expectedMessage = "Returning Customer";
        String actualMessage = getTextFromElement(By.xpath("//h2[normalize-space()='Returning Customer']"));
        verifyText("The expected text is not displayed", expectedMessage, actualMessage);

    }

    String email;
    String password = "Abcd1234";

    @Test
    public void verifyThatUserRegisterAccountSuccessfully() throws InterruptedException {

        //Clickr on My Account Link.
        String name = UUID.randomUUID().toString();
        email = name + "@gmail.com";
        //Call the method “selectMyAccountOptions” method and pass the parameter
        //“Register”
        selectMyAccountOptions("Register");

        //Enter First Name
        sendTextToElement(By.xpath("//input[@id='input-firstname']"), "Michael");
        //Enter Last Name
        sendTextToElement(By.xpath("//input[@id='input-lastname']"), "Vine");
        //Enter Email
        sendTextToElement(By.xpath("//input[@id='input-email']"), email);
        //Enter Telephone
        sendTextToElement(By.xpath("//input[@id='input-telephone']"), "07788995566");
        //Enter Password
        sendTextToElement(By.xpath("//input[@id='input-password']"), password);
        //Enter Password Confirm
        sendTextToElement(By.xpath("//input[@id='input-confirm']"), password);
        //Select Subscribe Yes radio button
        clickOnElement(By.xpath("//label[normalize-space()='Yes']"));
        //Click on Privacy Policy check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        //Click on Continue button
        clickOnElement(By.xpath("//input[@value='Continue']"));

        /**
         * Verify the message “Your Account Has Been Created!”
         */
        String expectedMessage = "Your Account Has Been Created!";
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Your " +
                "Account Has Been Created!']"));
        verifyText("The expected text is not displayed", expectedMessage, actualMessage);

        //Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

        //Click on My Account Link.
        selectMyAccountOptions("Logout");

        /**
         * Verify the text “Account Logout
         */
        String expectedMes = "Account Logout";
        String actualMes = getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']"));
        verifyText("The expected text is not displayed", expectedMes, actualMes);

        //Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }

    @Test
    public void verifyThatUserShouldLoginAndLogoutSuccessfully() throws InterruptedException {

        //Click on My Account Link.
        selectMyAccountOptions("Login");
        //Call the method “selectMyAccountOptions” method and pass the parameter
        //“Login”
        sendTextToElement(By.xpath("//input[@id='input-email']"), "michael12@gmail.com");
        sendTextToElement(By.xpath("//input[@id='input-password']"), "Asdf1234");
        //Click on Login button
        clickOnElement(By.xpath("//input[@value='Login']"));

        /**
         * Verify text “My Account”
         */
        String expectedMes = "My Account";
        String actualMes = getTextFromElement(By.xpath("//h2[normalize-space()='My Account']"));
        verifyText("The expected text is not displayed", expectedMes, actualMes);

        //Click on My Account Link.
        selectMyAccountOptions("Logout");

        /**
         * Verify the text “Account Logout
         */
        String expectedMessage = "Account Logout";
        String actualMessage = getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']"));
        verifyText("The expected text is not displayed", expectedMessage, actualMessage);

        //Click on Continue button
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));
    }
}
