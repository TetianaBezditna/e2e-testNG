import base.BaseTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestValidationLoginForm extends BaseTest {

    @ParameterizedTest
    @CsvSource({
            "Admin, admin1231",
            "Admin1, admin123"
    })
    public void checkValidationLoginForm(String name, String password){
        loginPage.open();
        loginPage.fillLoginForm(name, password);
        loginPage.clickLoginButton();
        loginPage.checkMessage("Invalid credentials");

    }

}
