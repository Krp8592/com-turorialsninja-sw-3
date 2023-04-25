package desktops;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DesktopsTest extends Utility {
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
    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {

        //Mouse hover on Desktops Tab.and click
        actions().moveToElement(element(By.xpath("//a[normalize-space()='Desktops']"))).perform();
        //Click on “Show All Desktops”
        selectMenu("Show AllDesktops");
        Thread.sleep(1000);

        /**
         * Verify the Product will arrange in Descending order
         */
        List<WebElement> beforeSortingValue = driver.findElements(By.xpath("//div[@class = " +
                "'caption']//h4/a"));
        List<String> beforeSortingDesktopValue = new ArrayList<>();
        for (WebElement element : beforeSortingValue) {
            beforeSortingDesktopValue.add(element.getText());
        }

        //Select Sort By position "Name: A to Z"
        clickOnElement(By.xpath("//select[@id='input-sort']"));
        selectByVisibleText(By.tagName("select"), "Name (Z - A)");
        Thread.sleep(1000);
        List<WebElement> afterSortingValue = driver.findElements(By.xpath("//div[@class = " +
                "'caption']//h4/a"));
        List<String> afterSortingDesktopValue = new ArrayList<>();
        for (WebElement element : afterSortingValue) {
            afterSortingDesktopValue.add(element.getText());
        }

        beforeSortingDesktopValue.sort(String.CASE_INSENSITIVE_ORDER);
        Collections.reverse(beforeSortingDesktopValue);
        verifyText("Displayed items are not according to reverse order",
                beforeSortingDesktopValue.toString(), afterSortingDesktopValue.toString());

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        //Mouse hover on Desktops Tab. and click
        actions().moveToElement(element(By.xpath("//a[normalize-space()='Desktops']"))).perform();
        //Click on “Show All Desktops
        selectMenu("Show AllDesktops");
        //Select Sort By position "Name: A to Z
        clickOnElement(By.xpath("//select[@id='input-sort']"));
        selectByVisibleText(By.tagName("select"), "Name (A - Z)");
        //Select product “HP LP3065”
        clickOnElement(By.xpath("//a[normalize-space()='HP LP3065']"));
        /**
         * Verify the Text "HP LP3065"
         */
        String expectedText = "HP LP3065";
        String actualText = getTextFromElement(By.xpath("//h1[normalize-space()='HP LP3065']"));
        verifyText("The expected text 'HP LP3065' is not displayed", expectedText, actualText);

        Thread.sleep(1000);

        //Select Delivery Date "2022-11-30"
        selectDate("30", "November", "2022");

        Thread.sleep(2000);

        //Enter Qty "1” using Select class
        sendTextToElement(By.xpath("//input[@id='input-quantity']"), "1");

        //Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));

        /**
         * Verify the Message “Success: You have added HP LP3065 to your shopping cart!”
         */
        String expectedMessage = "Success: You have added HP LP3065 to your shopping cart!\n";
        String actualMessage1 = getTextFromElement(By.xpath("//div[@class='alert" +
                " alert-success alert-dismissible']"));
        String[] actualMessage = actualMessage1.split("×");
        verifyText("Success message is not displayed", actualMessage[0], expectedMessage);

        //Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[normalize-space()='shopping cart']"));
        Thread.sleep(1000);

        /**
         * Verify the text "Shopping Cart"
         */
        String expectedTxt = "Shopping Cart  (1.00kg)";
        String actualTxt = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping Cart')]"));
        verifyText("The expected text 'Shopping Cart' is not displayed", expectedTxt, actualTxt);

        /**
         * Verify the Product name "HP LP3065"
         */
        String expectedProName = "HP LP3065";
        String actualProName = getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/form[1]/div[1]" +
                "/table[1]/tbody[1]/tr[1]/td[2]/a[1]"));
        verifyText("The expected product name 'HP LP3065' is not displayed", expectedProName, actualProName);

        /**
         * Verify the Delivery Date "2022-11-30"
         */
        String expectedDate = "2022-11-30";
        String actualDate = getTextFromElement(By.xpath("(//small)[2]"));
        String[] actualDate1 = actualDate.split(":");
        verifyText("The expected product name '2022-11-30' is not displayed", expectedDate, actualDate1[1]);

        /**
         * Verify the Model "Product21"
         */
        String expectedModel = "Product 21";
        String actualModel = getTextFromElement(By.xpath("//td[normalize-space()='Product 21']"));
        verifyText("The expected model 'Product 21", expectedModel, actualModel);

        /**
         * Verify the Todat "£74.73"
         */
        String expectedTotal = "$122.00";
        String actualTotal = getTextFromElement(By.xpath("(//td[normalize-space()='$122.00'])[4]"));
        verifyText("The expected price total '$122.00' is not displayed", expectedTotal, actualTotal);


    }


    @After
    public void tearDown() {
        closeBrowser();
    }


    /**
     * Method for datePicking from the date options.
     *
     * @param date
     * @param month
     * @param year
     * @throws InterruptedException
     */
    public void selectDate(String date, String month, String year) throws InterruptedException {

        clickOnElement(By.xpath("//i[@class='fa fa-calendar']"));
        while (true) {
            String monthYear = driver.findElement(By.xpath("(//th[@class='picker-switch'])[1]")).getText();
            String[] a = monthYear.split(" ");
            String mon = a[0];
            String yer = a[1];
            if (mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)) {
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next'][contains(text(),'›')]"));
            }
        }
        Thread.sleep(1000);
        // Select the date
        List<WebElement> allDates = driver.findElements(By.xpath("//*[@class='datepicker-days']//tbody//tr//td"));
        for (WebElement dt : allDates) {
            if (dt.getText().equalsIgnoreCase(date)) {
                dt.click();
                break;
            }
        }


    }
}

