package base;

import gui.core.WebDriverFactory;
import gui.pages.*;
import gui.steps.ContactDetailsSteps;
import gui.steps.LoginSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static gui.core.WebDriverContainer.containerDriver;

public abstract class BaseTest {
    protected LoginSteps loginSteps = new LoginSteps();
    protected LoginPage loginPage = new LoginPage();
    protected ResetPage resetPage = new ResetPage();
    protected ContactDetailsSteps contactDetailsSteps = new ContactDetailsSteps();
    protected ContactDetailsPage contactPage = new ContactDetailsPage();
    protected LeftMenuPage menuPage = new LeftMenuPage();
    protected MyInfoPage myInfoPage = new MyInfoPage();

    @BeforeEach
    public void setUp() {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        containerDriver().setWebDriver(driver);
    }

    @AfterEach
    public void teardown() {
        WebDriver driver = containerDriver().getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}
