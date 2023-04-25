package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {

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
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully() {

        //Mouse hover on “Desktops” Tab and click
        actions().moveToElement(element(By.xpath("//a[normalize-space()='Desktops']"))).perform();
        //call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show AllDesktops");
        /**
         * Verify the text ‘Desktops’
         */
        String expectedText = "Desktops";
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Desktops']"));
        verifyText("The expected text 'Desktops' is not displayed", expectedText, actualText);

    }


    @Test
    public void verifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully() {

        //Mouse hover on “Laptops & Notebooks” Tab and click
        actions().moveToElement(element(By.xpath("//a[normalize-space()='Laptops" +
                " & Notebooks']"))).perform();
        //call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("Show AllLaptops & Notebooks");

        /**
         * Verify the text ‘Laptops & Notebooks’
         */
        String expectedText = "Laptops & Notebooks";
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Laptops & Notebooks']"));
        verifyText("The expected text 'Laptops & Notebooks' is not displayed", expectedText, actualText);

    }

    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully() {

        //Mouse hover on “Components” Tab and click
        actions().moveToElement(element(By.xpath("//a[normalize-space()='Components']"))).perform();
        //call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show AllComponents");

        //Verify the text ‘Components’
        String expectedText = "Components";
        String actualText = getTextFromElement(By.xpath("//h2[normalize-space()='Components']"));
        verifyText("The expected text 'Laptops & Notebooks' is not displayed", expectedText, actualText);

    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
