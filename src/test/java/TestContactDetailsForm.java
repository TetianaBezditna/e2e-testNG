
import base.BaseTest;
import org.junit.jupiter.api.*;
public class TestContactDetailsForm  extends BaseTest {
    @Test
    public void upgradeContactDetails() {

        final String userName = "Admin";
        final String password = "admin123";
        final String secondStreetField = "\'Street 2\'";
        final String stateField = "\'State/Province\'";
        final String homePhoneField = "\'Home\'";
        final String workEmailField = "\'Work Email\'";
        final String expectedSecondStreet;
        final String expectedHomePhoneName;
        final String expectedWorkEmail;
        final String expectedState;
        final String countryName;

        loginSteps.login(userName, password);
        menuPage.openTheMyInfoCategory();
        myInfoPage.openContactDetailsCategory();
        contactPage.clearForm();
        expectedSecondStreet = contactPage.fillField(secondStreetField, "test Street ");
        expectedState = contactPage.fillField(stateField, "test State ");
        expectedHomePhoneName = contactPage.fillField(homePhoneField, "+");
        expectedWorkEmail = contactPage.fillField(workEmailField, "test@gmail.com");;
        countryName = contactDetailsSteps.selectCountry();
        contactPage.saveForm();
        contactPage.waitForToastToDisappear();
        contactPage.refreshPage();
        contactPage.waitFieldNameIsPresent(expectedSecondStreet);
        //check
        contactPage.checkStreet(expectedSecondStreet);
        contactPage.checkCountry(countryName);
        contactPage.checkState(expectedState);
        contactPage.checkHomePhoneField(expectedHomePhoneName);
        contactPage.checkEmailField(expectedWorkEmail);

    }
}