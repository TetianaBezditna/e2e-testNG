package gui.steps;

public class ContactDetailsSteps extends BaseSteps {
    public String selectCountry() {
        contactDetailsPage.openTheItemList();
        String country = contactDetailsPage.getRandomCountryName();
        contactDetailsPage.selectCountryInList(country);
        return country;

    }
}
