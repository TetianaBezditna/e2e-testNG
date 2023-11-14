package gui.steps;
import gui.pages.ContactDetailsPage;
import gui.pages.LoginPage;


public abstract class BaseSteps {
    LoginPage loginPage = new LoginPage();
    ContactDetailsPage contactDetailsPage = new ContactDetailsPage();

}