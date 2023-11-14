
import base.BaseTest;
import org.junit.jupiter.api.*;
public class TestLogin extends BaseTest {

    @Test
    public void loginInSite (){

        final String userName = "Admin";
        final String password = "admin123";
        final String pageTitle = "OrangeHRM";
        final String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        loginSteps.login(userName, password);
        loginPage.checkPageTitle(pageTitle);
        loginPage.checkURL(expectedURL);
    }

}