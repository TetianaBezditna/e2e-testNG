package gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static gui.core.WebDriverContainer.containerDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BasePage {

    protected WebDriver getDriver() {
       return containerDriver().getDriver();
    }

    public String getPageTitle(String pageTitle ){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(pageTitle));
        return getDriver().getTitle();
    }
    public String getPageURL() {
        return getDriver().getCurrentUrl();
    }

    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    public void open(String URL) {
        getDriver().get(URL);
    }
    public By getLocator(String value) {
        return By.xpath(value);
    }
    public void inputText(String locator, String text){
        getDriver().findElement(getLocator(locator)).sendKeys(text);

    }
    public void click(String locator){
        getDriver().findElement(getLocator(locator)).click();
    }

    public void checkURL(String URL){
        assertEquals(URL, getPageURL());
    }

    public String getElementText (String locator){
        return  getDriver().findElement(getLocator(locator)).getText();
    }

    public String getAttribute(String locator, String value){
        return   getDriver().findElement(getLocator(locator)).getAttribute(value);
    }
    public void checkText(String expectedValue, String locator){
        assertEquals(expectedValue, getElementText(locator));
    }


    public Boolean getElementVisibilityResult(String locator){
      return getDriver().findElement(getLocator(locator)).isDisplayed();

    }

    /**
     * Clear field value
     */
    public void clearField() {
        Actions builder = new Actions(getDriver());
        builder.keyDown(Keys.COMMAND)
                .sendKeys("A")
                .keyUp(Keys.COMMAND)
                .keyDown(Keys.DELETE)
                .perform();
    }

    /**
     * Get random element from list
     * @param list   list with items
     * @return random item
     * @param <T>
     */
    public <T> T getRandomElement(List<T> list) {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    /**
     * Get list value
     * @return List <String>
     */
    public  List<String> getListValue(String locator) {
        List<String> listValue = new ArrayList<>();
        List<WebElement> listWebElements = getDriver().findElements(getLocator(locator));
        String listElement;
        for (int i = 1; i < listWebElements.size(); i++) {

            String xpathRackFilter = String.format(locator + "[" + i + "]", i);
            By xpathRack = By.xpath(xpathRackFilter);
            listElement = getDriver().findElement(xpathRack).getText();
            listValue.add(listElement);
        }
        return listValue;
    }

    public Integer getRandomNum (){
        Random rn = new Random();
        int maximum = 10000;
        int minimum = 0;
        return rn.nextInt(maximum - minimum + 1) + minimum;
    }

    public void waitForElementToDisappear (String value, int timeout){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.invisibilityOf(getDriver().findElement(getLocator(value))));
    }

    public void waitTextToBePresentInElementValue(String value, String locator, int timeout){
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.textToBePresentInElementValue(getLocator(locator), value));
    }
}
