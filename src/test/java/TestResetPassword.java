
import base.BaseTest;
import org.junit.jupiter.api.*;
public class TestResetPassword extends BaseTest {

    final String userName = "Admin";
    @Test
    public void testForgotPasswordForm (){
        final String expectedURL= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
        final String expectedFormTitle ="Reset Password";
        final String fieldNameInModalWindow = "Username";
        final String expectedDescription = "Please enter your username to identify your account to reset your password";

         loginPage.open();
         loginPage.clickForgotButton();
         resetPage.checkURL(expectedURL);
         resetPage.checkFormTitle(expectedFormTitle);
         resetPage.checkDescription(expectedDescription);
         resetPage.checkNameInModalWindow(fieldNameInModalWindow);
         resetPage.checkPlaceholder(fieldNameInModalWindow);
         resetPage.checkCancelButton();
         resetPage.checkResetPasswordButton();

    }

    @Test
    public void resetPassword (){
        final String expectedURL= "https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset";
        final String formTitle ="Reset Password link sent successfully";
        final String firstDescription ="A reset password link has been sent to you via email.";
        final String secondDescription = "You can follow that link and select a new password.";
        final String note = "If the email does not arrive, please contact your OrangeHRM Administrator.";

        loginPage.open();
        loginPage.clickForgotButton();
        resetPage.fillResetPasswordForm(userName);
        resetPage.clickResetPasswordButton();
        resetPage.checkURL(expectedURL);
        resetPage.checkFormTitle(formTitle);
        resetPage.checkInfoInTheForm(firstDescription, 2);
        resetPage.checkInfoInTheForm(secondDescription, 4);
        resetPage.checkInfoInTheForm(note, 7);

    }
    @Test
    public void cancelResetPassword (){

        loginPage.open();
        String URL = loginPage.getCurrentURL();
        loginPage.clickForgotButton();
        resetPage.clickCancelButton();
        resetPage.checkURL(URL);
    }
    @Test
    public void validationResetPasswordForm (){

        final String message = "Required";
        loginPage.open();
        loginPage.clickForgotButton();
        resetPage.clickResetPasswordButton();
        resetPage.checkValidationMessage(message);
    }

}