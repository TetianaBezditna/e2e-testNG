package gui.core;

import org.openqa.selenium.WebDriver;

public class WebDriverContainer {
    private static WebDriverContainer container;
    private WebDriver driver;

    private WebDriverContainer() {

    }

    public static WebDriverContainer containerDriver() {
        if (container == null) {
            container = new WebDriverContainer();
        }

        return container;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void setWebDriver(WebDriver webDriver) {
        this.driver = webDriver;
    }
}
