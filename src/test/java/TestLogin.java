
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;


public class TestLogin {
    private static WebDriver driver;
    /**
     @BeforeAll
     public static void initWebDriver(){
     driver = new ChromeDriver();
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
     }
     */

    @BeforeEach
    public void initWebDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    /**

     @AfterAll
     public static void closeWebDriver(){
     driver.close();
     driver.quit();
     }
     */

    @AfterEach
    public void closeWebDriver(){
        driver.close();
        driver.quit();
    }


    @Test
    public void loginInSite (){

        final String userName = "Admin";
        final String password = "admin123";
        final String userNameField = "//input[@name='username']";
        final String passwordField = "//input[@name='password']";
        final String buttonLogin = "//button[text()=' Login ']";
        final String pageTitle = "OrangeHRM";
        final String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        //open the login form
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // input username
        driver.findElement(By.xpath(userNameField)).sendKeys(userName);

        // input the password
        driver.findElement(By.xpath(passwordField)).sendKeys(password);

        // Click on the button 'Login'
        driver.findElement(By.xpath(buttonLogin)).click();

        // check the title on the page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs(pageTitle));
        driver.getTitle();
        assertEquals(pageTitle,    driver.getTitle(), "title = " +   driver.getTitle());

        //checked URL
        assertEquals(expectedURL,  driver.getCurrentUrl());

    }

}