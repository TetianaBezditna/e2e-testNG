package gui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                return configChrome();
            case "firefox":
                return configFirefox();
            case "edge":
                return configEdge();
            default:
                System.out.println("Wrong browser name: " + browser);
                return configChrome();
        }
    }

    private static ChromeDriver configChrome() {
        ChromeOptions options = new ChromeOptions();
        options.setImplicitWaitTimeout(Duration.ofMillis(10000));
        return new ChromeDriver(options);
    }

    private static FirefoxDriver configFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.setImplicitWaitTimeout(Duration.ofMillis(10000));
        return new FirefoxDriver(options);
    }

    private static EdgeDriver configEdge() {
        EdgeOptions options = new EdgeOptions();
        options.setImplicitWaitTimeout(Duration.ofMillis(10000));
        return new EdgeDriver(options);
    }
}
