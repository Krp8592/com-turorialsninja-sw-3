package laptopsandnotebooks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {

    String baseUrl = "http://tutorialsninja.com/demo/index.php?";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    /**
     * Create method with name "selectMenu" it has one parameter name "menu" of type string
     * This method should click on the menu whatever name is passed as parameter
     */
    public void selectMenu(String menu) {
        //Generic X-path for menu navigation
        clickOnElement(By.xpath("//div[@class='collapse navbar-collapse navbar-ex1-collapse']" +
                "//a[normalize-space()='" + menu + "']"));
    }

    @Test
    public void verifyProductsPriceDisplayHighToLowSuccessfully() throws InterruptedException {

        //Mouse hover on Laptops & Notebooks Tab.and click
        actions().moveToElement(element(By.xpath("//a[normalize-space()='Laptops" +
                " & Notebooks']"))).perform();
        //Click on “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
        Thread.sleep(1000);
        //Select Sort By "Price (High > Low)"
        clickOnElement(By.xpath("//select[@id='input-sort']"));
        selectByVisibleText(By.tagName("select"), "Price (High > Low)");
        Thread.sleep(1000);

        /**
         * Verify the Product price will arrange in High to Low order.
         */
        int x = 1;
        int y = 1;
        List<WebElement> priceBeforeShorting = driver.findElements(By.xpath("//*[contains(@class, 'price')" +
                "and not(contains(@class,'price-tax'))]"));
        List<String> desktopPriceBeforeSorting = new ArrayList<>();
        Thread.sleep(1000);
        for (WebElement price : priceBeforeShorting) {
            String priceBeforeShorting1 = String.valueOf(price.findElement(By.xpath("(//p[@class = 'price'])" +
                    "[" + x + "]")));
            String[] priceList = priceBeforeShorting1.split("E");
            desktopPriceBeforeSorting.add((priceList[0].replace("$", "")));
            x++;
        }

        clickOnElement(By.xpath("//select[@id='input-sort']"));
        selectByVisibleText(By.tagName("select"), "Price (High > Low)");

        Thread.sleep(1000);
        List<WebElement> priceAfterShorting = driver.findElements(By.xpath("//*[contains(@class, 'price')" +
                "and not(contains(@class,'price-tax'))]"));
        List<String> desktopPriceAfterShorting = new ArrayList<>();
        for (WebElement price1 : priceAfterShorting) {
            String priceAfterShorting1 = String.valueOf(price1.findElement(By.xpath("(//p[@class = 'price'])" +
                    "[" + y + "]")));
            String[] priceList = priceAfterShorting1.split("E");
            desktopPriceAfterShorting.add((priceList[0].replace("$", "")));
            y++;
        }

        verifyText("Prices are not sorted High to Low", desktopPriceBeforeSorting.toString(),
                desktopPriceAfterShorting.toString());


    }

    @Test
    public void verifyThatUserPlaceOrderSuccessfully() throws InterruptedException {

        //Mouse hover on Laptops & Notebooks Tab and click
        actions().moveToElement(element(By.xpath("//a[normalize-space()='Laptops" +
                " & Notebooks']"))).perform();
        Thread.sleep(2000);
        //Click on “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");
        Thread.sleep(1000);
        //Select Sort By "Price (High > Low)"
        clickOnElement(By.xpath("//select[@id='input-sort']"));
        selectByVisibleText(By.tagName("select"), "Price (High > Low)");
        Thread.sleep(1000);
        //Select Product “MacBook
        clickOnElement(By.xpath("//a[normalize-space()='MacBook']"));
        Thread.sleep(1000);

        /**
         * Verify the text “MacBook”
         */
        String expectedText = "MacBook";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='MacBook']"));
        verifyText("Expected text is not displayed", expectedText, actualText);

        //Click on ‘Add To Cart’ button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        /**
         * Verify the message “Success: You have added MacBook to your shopping cart!”
         */
        String expectedMessage = "Success: You have added MacBook to your shopping cart!\n";
        String actualMessage1 = getTextFromElement(By.xpath("//div[@class='alert" +
                " alert-success alert-dismissible']"));
        String[] actualMessage = actualMessage1.split("×");
        verifyText("Success message is not displayed", actualMessage[0], expectedMessage);

        //Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));

        /**
         * Verify the text "Shopping Cart"
         */
        String expectedTxt = "Shopping Cart  (0.00kg)";
        String actualTxt = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
        verifyText("The expected text 'Shopping Cart  (0.00kg)' is not displayed", expectedTxt, actualTxt);

        /**
         * Verify the Product name "MacBook"
         */
        String expectedMsg = "MacBook";
        String actualMsg = getTextFromElement(By.xpath("(//a[contains(text(),'MacBook')])[2]"));
        verifyText("The expected text is not displayed", expectedMsg, actualMsg);

        //Change Quantity "2"
        sendTextToElement(By.xpath("(//input[@class='form-control'])[1]"), "2");
        //Click on “Update” Tab
        clickOnElement(By.xpath("//i[@class='fa fa-refresh']"));

        /**
         * Verify the message “Success: You have modified your shopping cart!”
         */
        String expectedMes = "Success: You have modified your shopping cart!\n";
        String actualMes = getTextFromElement(By.xpath("//div[@class='alert alert-success " +
                "alert-dismissible']"));
        String[] actualMes1 = actualMes.split("×");
        verifyText("Success message is not displayed", actualMes1[0], expectedMes);

        /**
         * Verify the Total £737.45
         */
        String expectedTotal = "$1,204.00";
        String actualTotal = getTextFromElement(By.xpath("//tbody//tr//td[6]"));
        verifyText("The expected price total '$122.00' is not displayed", expectedTotal, actualTotal);

        //Click on “Checkout” button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));

        /**
         * Verify the text “New Customer”
         */
        String expectedMs = "New Customer";
        String actualMs = getTextFromElement(By.xpath("//h2[normalize-space()='New Customer']"));
        Thread.sleep(2000);
        verifyText("The expected text is not displayed", expectedMs, actualMs);

        //Click on “Guest Checkout” radio button
        clickOnElement(By.xpath("//input[@value='guest']"));
        clickOnElement(By.xpath("//input[@id='button-account']"));

        //Fill the mandatory fields
        sendTextToElement(By.xpath("//input[@id='input-payment-firstname']"), "Michael");
        sendTextToElement(By.xpath("//input[@id='input-payment-lastname']"), "Vine");
        sendTextToElement(By.xpath("//input[@id='input-payment-email']"), "Michael123@gmail.com");
        sendTextToElement(By.xpath("//input[@id='input-payment-telephone']"), "07788994455");
        sendTextToElement(By.xpath("//input[@id='input-payment-address-1']"), "123 Abc Street ");
        sendTextToElement(By.xpath("//input[@id='input-payment-city']"), "London");
        sendTextToElement(By.xpath("//input[@id='input-payment-postcode']"), "CR06AQ");

        selectByVisibleText(By.xpath("//select[@id='input-payment-country']"), "United Kingdom");
        selectByVisibleText(By.xpath("//select[@id='input-payment-zone']"), "Cardiff");
        //Click on “Continue” Button
        clickOnElement(By.xpath("//input[@id='button-guest']"));
        //Add Comments About your order into text area
        sendTextToElement(By.xpath("//textarea[@name='comment']"), "Thanks");
        //Check the Terms & Conditions check box
        clickOnElement(By.xpath("//input[@name='agree']"));
        //Click on “Continue” button
        clickOnElement(By.xpath("//input[@id='button-payment-method']"));

        /**
         * Verify the message “Warning: Payment method required!”
         */
        String expMsg = "Warning: Payment method required!";
        String actMeg = getTextFromElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']"));
        String[] actMsg1 = actMeg.split("\n×");
        verifyText("Warning msg not displayed", expMsg, actMsg1[0]);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

}
