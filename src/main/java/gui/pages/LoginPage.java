package gui.pages;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPage extends BasePage {
    private final String userNameXpath = "//input[@name='username']";
    private final String passwordFieldXpath = "//input[@name='password']";
    private final String buttonLoginXpath = "//button[@type='submit' and contains(normalize-space(), 'Login')]";
    private final String pageURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private final String forgotPasswordButtonXpath ="//div[@class='orangehrm-login-forgot']/p";
    private final String validationMessageXpath = "(//div[@class='orangehrm-login-error']//p)[1]";

    /**
     * Open the login page
     */
    public void open() {
        open(pageURL);
    }

    /**
     * Fill the login form
     * @param userName login
     * @param password password
     */
    public void fillLoginForm(String userName, String password) {
        inputText(userNameXpath, userName);
        inputText(passwordFieldXpath, password);
    }

    /**
     * Click on the 'Login' button
     */
    public void clickLoginButton() {
        click(buttonLoginXpath);
    }

    public void checkPageTitle(String pageTitle){
        assertEquals(pageTitle,   getPageTitle(pageTitle), "title = " +   pageTitle);
    }

    public void clickForgotButton(){
        click(forgotPasswordButtonXpath);
    }

    public String getCurrentURL(){
        return getPageURL();
    }

    public void checkMessage (String value) {
        checkText(value, validationMessageXpath);
    }

}
