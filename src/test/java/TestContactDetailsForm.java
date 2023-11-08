
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestContactDetailsForm {
    private static WebDriver driver;

    /**
     * Clear field value
     */
    private void clearField() {
        Actions builder = new Actions(driver);
        builder.keyDown(Keys.COMMAND)
                .sendKeys("A")
                .keyUp(Keys.COMMAND)
                .keyDown(Keys.DELETE)
                .perform();
    }

    /**
     * the path to an element on the page
     * @param value text value
     * @return String
     */
    private String xpathInputFields(String value) {
        return "//div[@class='oxd-form-row']//label[text()=" + value + "]//ancestor::div[2]//input";

    }

    /**
     * Get random element from list
     * @param list   list with items
     * @return random item
     * @param <T>
     */
    public static <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    /**
     * Get list value
     * @return List <String>
     */
    public static List<String> getListValue() {
        List<String> listValue = new ArrayList<>();
        List<WebElement> listWebElements = driver.findElements(By.xpath("//div[@role='listbox']//span"));
        String listElement;
        for (int i = 1; i < listWebElements.size(); i++) {

            String xpathRackFilter = String.format("(//div[@role='listbox']//span)" + "[" + i + "]", i);
            By xpathRack = By.xpath(xpathRackFilter);
            listElement = driver.findElement(xpathRack).getText();
            listValue.add(listElement);
        }
        return listValue;
    }

    @Test

    public void upgradeContactDetails() {
        final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
        final String userName = "Admin";
        final String password = "admin123";
        final String userNameField = "//input[@name='username']";
        final String passwordField = "//input[@name='password']";
        final String buttonLogin = "//button[text()=' Login ']";
        final String myInfoCategory = "//ul[@class='oxd-main-menu']//span[text()='My Info']";
        final String contactDetails = "//div[@class='orangehrm-edit-employee-navigation']//a[text()='Contact Details']";
        final String selectedCountry = "//div[@class='oxd-form-row']//div[@class='oxd-select-text-input']";
        final String expandListCountry = "//div[starts-with(@class, 'oxd-grid-item')]//i";
        final String toastText = "//p[text()='Successfully Updated']";
        final String saveButton = "//button[text()=' Save ']";
        final String pathForItemInList = "//div[@role='listbox']//span[text()=";
        final String secondStreetField = "\'Street 2\'";
        final String stateField = "\'State/Province\'";
        final String homePhoneField = "\'Home\'";
        final String workEmailField = "\'Work Email\'";
        final String expectedSecondtStreet;
        final String expectedHomePhoneName;
        final String expectedWorkEmail;
        final String expectedState;

        Random rn = new Random();
        int maximum = 10000;
        int minimum = 0;
        int randomNum = rn.nextInt(maximum - minimum + 1) + minimum;


        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            //open the login page
            driver.get(URL);
            //full the login form
            driver.findElement(By.xpath(userNameField)).sendKeys(userName);
            driver.findElement(By.xpath(passwordField)).sendKeys(password);
            // Click on the button 'Login'
            driver.findElement(By.xpath(buttonLogin)).click();
            //Go to the 'My info' category
            driver.findElement(By.xpath(myInfoCategory)).click();
            //Go to the 'Contact Details' category
            driver.findElement(By.xpath(contactDetails)).click();
            //delete the value in the 'Street 2' field
            driver.findElement(By.xpath(xpathInputFields(secondStreetField))).click();
            clearField();
            //input new value and save it
            driver.findElement(By.xpath(xpathInputFields(secondStreetField)))
                    .sendKeys(expectedSecondtStreet = "test Street " + randomNum);

            //delete the value in the 'State/Province field'
            driver.findElement(By.xpath(xpathInputFields(stateField))).click();
            clearField();

            //input value State/Province field
            driver.findElement(By.xpath(xpathInputFields(stateField)))
                    .sendKeys(expectedState = "test State " + randomNum);

            // expand list in the 'Country' field,
            driver.findElement(By.xpath(expandListCountry)).click();

            // select a random value and click on it
            String countryName = getRandomElement(getListValue());
            driver.findElement(By.xpath(pathForItemInList + "\'" + countryName + "\'" + "]")).click();

            //delete the value in the 'Home' field telephone section
            driver.findElement(By.xpath(xpathInputFields(homePhoneField))).click();
            clearField();
            //input new value and save it
            driver.findElement(By.xpath(xpathInputFields(homePhoneField)))
                    .sendKeys(expectedHomePhoneName = "+" + randomNum);

            //delete the value in the 'Work email'
            driver.findElement(By.xpath(xpathInputFields(workEmailField))).click();
            clearField();
            //input new value and save it
            driver.findElement(By.xpath(xpathInputFields(workEmailField)))
                    .sendKeys(expectedWorkEmail = "test" + randomNum + "@osohrm.com");

            //Click on the 'Save' button
            driver.findElement(By.xpath(saveButton)).click();

            //wait until the toast with the message about successful saving stops being displayed
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath(toastText))));
            //refresh page
            driver.navigate().refresh();
            //We are waiting for the page to be drawn
            wait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath(xpathInputFields(secondStreetField)), expectedSecondtStreet));

            /**
             * check value on the form
             */

            //check Street 2 field
            String actualStreetValue = driver.findElement(By.xpath(xpathInputFields(secondStreetField))).getAttribute("value");
            assertEquals(expectedSecondtStreet, actualStreetValue);

            // check value in the 'Country' field
            String actualCountry = driver.findElement(By.xpath(selectedCountry)).getText();
            assertEquals(countryName, actualCountry);

            // check State/Province field
            String actualStateValue = driver.findElement(By.xpath(xpathInputFields(stateField))).getAttribute("value");
            assertEquals(expectedState, actualStateValue);

            // check Home field
            String actualHomePhoneField = driver.findElement(By.xpath(xpathInputFields(homePhoneField))).getAttribute("value");
            assertEquals(expectedHomePhoneName, actualHomePhoneField);

            // check Home field
            String actualWorkEmailField = driver.findElement(By.xpath(xpathInputFields(workEmailField))).getAttribute("value");
            assertEquals(expectedWorkEmail, actualWorkEmailField);

        } catch (Error e) {
            throw new RuntimeException(e);
        } finally {
            driver.quit();
        }

    }
}