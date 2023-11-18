
import base.BaseTest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestResetPassword extends BaseTest {

    final String userName = "Admin";

    @Test
    public void testForgotPasswordForm() {
        final String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/requestPasswordResetCode";
        final String expectedFormTitle = "Reset Password";
        final String fieldNameInModalWindow = "Username";
        final String expectedDescription = "Please enter your username to identify your account to reset your password";

        loginPage.open();
        loginPage.clickForgotButton();
        //check
        assertEquals(expectedURL, resetPage.getPageURL());
        assertEquals(expectedFormTitle, resetPage.getFormTitle());
        assertEquals(expectedDescription, resetPage.getDescription());
        assertEquals(fieldNameInModalWindow, resetPage.getNameInModalWindow());
        assertEquals(fieldNameInModalWindow, resetPage.getPlaceholder());
        assertTrue(resetPage.getCancelButton());
        assertTrue(resetPage.getResetPasswordButton());

    }

    @Test
    public void resetPassword() {
        final String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/sendPasswordReset";
        final String formTitle = "Reset Password link sent successfully";
        final String firstDescription = "A reset password link has been sent to you via email.";
        final String secondDescription = "You can follow that link and select a new password.";
        final String note = "If the email does not arrive, please contact your OrangeHRM Administrator.";

        loginPage.open();
        loginPage.clickForgotButton();
        resetPage.fillResetPasswordForm(userName);
        resetPage.clickResetPasswordButton();
        //check
        assertEquals(expectedURL, resetPage.getPageURL());
        assertEquals(formTitle, resetPage.getFormTitle());
        assertEquals(firstDescription, resetPage.getInfoInTheForm(2));
        assertEquals(secondDescription, resetPage.getInfoInTheForm(4));
        assertEquals(note, resetPage.getInfoInTheForm(7));

    }

    @Test
    public void cancelResetPassword() {

        loginPage.open();

        String URL = loginPage.getPageURL();

        loginPage.clickForgotButton();

        resetPage.clickCancelButton();
        //check
        assertEquals(URL, resetPage.getPageURL());

    }
    @Test
    public void validationResetPasswordForm() {

        final String message = "Required";

        loginPage.open();

        loginPage.clickForgotButton();

        resetPage.clickResetPasswordButton();
        //check
        assertEquals(message, resetPage.getValidationMessage());

    }

}