
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;


public class TestResetPassword {
    private static WebDriver driver;

    private final String URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String fieldNameInModalWindow = "Username";
    private final String forgotPasswordButton ="//div[@class='orangehrm-login-forgot']/p";
    private final String resetPasswordButton = "//form[@class='oxd-form']//button[text()=' Reset Password ']";
    private  final String cancelButton = "//form[@class='oxd-form']//button[text()=' Cancel ']";
    private final String inputField = "(//form[@class='oxd-form']//input)[2]";

    private String xpathDescription (int a ) {
        return  "(//div[@id='app']//p)" + "[" + a + "]";
    }

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
    public void testForgotPasswordForm (){
        final String titleForm = "//form[@class='oxd-form']/h6";
        final String descriptionForm = "(//form[@class='oxd-form']//p)[2]";
        final String fieldName = "//form[@class='oxd-form']//label";
        final String expectedURL= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";


        //open the login form
        driver.get(URL);
        //click on the - Forgot password button
        driver.findElement(By.xpath(forgotPasswordButton)).click();
        //checked URL
        assertEquals(expectedURL, driver.getCurrentUrl());
        //check title on the Form
        assertEquals("Reset Password",  driver.findElement(By.xpath(titleForm)).getText());
        //check the description on the form
        assertEquals("Please enter your username to identify your account to reset your password",
                driver.findElement(By.xpath(descriptionForm)).getText());
        //check the presence of the Username field
        assertEquals(fieldNameInModalWindow , driver.findElement(By.xpath(fieldName)).getText());
        //check the presence of the placeholder - Username
        assertEquals(fieldNameInModalWindow ,
                driver.findElement(By.xpath(inputField)).getAttribute("placeholder"));
        //check the presence of the 'Cancel' button
        assertTrue(driver.findElement(By.xpath(cancelButton)).isDisplayed());
        //check the presence of the 'Reset password' button
        assertTrue(driver.findElement(By.xpath(resetPasswordButton)).isDisplayed());
    }

    @Test
    public void resetPassword (){
        final String titleForm = "//div[@id='app']//h6";
        final String expectedURL= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset";

        //open the login form
        driver.get(URL);
        //click on the - Forgot password button
        driver.findElement(By.xpath(forgotPasswordButton)).click();
        //input username on the field
        driver.findElement(By.xpath(inputField)).sendKeys("Admin");
        // Click on the 'Reset Password' button
        driver.findElement(By.xpath(resetPasswordButton)).click();
        //checked URL
        assertEquals(expectedURL, driver.getCurrentUrl());
        //checked title form
        assertEquals("Reset Password link sent successfully", driver.findElement(By.xpath(titleForm)).getText());
        //checked description 1 on the form
        assertEquals("A reset password link has been sent to you via email.",
                driver.findElement(By.xpath(xpathDescription (2))).getText());
        //checked description 2 on the form
        assertEquals("You can follow that link and select a new password.",
                driver.findElement(By.xpath(xpathDescription(4))).getText());
        //checked note on the form
        assertEquals("If the email does not arrive, please contact your OrangeHRM Administrator.",
                driver.findElement(By.xpath(xpathDescription(7))).getText());

    }

    @Test
    public void cancelResetPassword (){

        //open the login form
        driver.get(URL);
        //click on the - Forgot password button
        driver.findElement(By.xpath(forgotPasswordButton)).click();
        // Click on the 'Cancel' button
        driver.findElement(By.xpath(cancelButton)).click();
        //checked the login form is opens (check URL)
        assertEquals(URL, driver.getCurrentUrl());
    }

    @Test
    public void validationResetPasswordForm (){
        final String validationMessage = "//div[@class='oxd-form-row']//span";

        //open the login form
        driver.get(URL);
        //click on the - Forgot password button
        driver.findElement(By.xpath(forgotPasswordButton)).click();
        // Click on the 'Reset Password' button
        driver.findElement(By.xpath(resetPasswordButton)).click();
        //check the validation message
        assertEquals("Required" , driver.findElement(By.xpath(validationMessage)).getText());
    }

}