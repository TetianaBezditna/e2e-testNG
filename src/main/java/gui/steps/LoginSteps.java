package gui.steps;


public class LoginSteps extends BaseSteps{
    public void login(String userName, String userPassword) {
        loginPage. open();
        loginPage.fillLoginForm(userName, userPassword);
        loginPage.clickLoginButton();
    }
}
