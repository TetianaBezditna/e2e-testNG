package gui.pages;

public class ContactDetailsPage extends BasePage {
    private final String selectedCountryXpath = "//div[@class='oxd-form-row']//div[@class='oxd-select-text-input']";
    private final String expandListCountryXpath = "//div[starts-with(@class, 'oxd-grid-item')]//i";
    private final String toastTextXpath = "//p[text()='Successfully Updated']";
    private final String saveButtonXpath = "//button[text()=' Save ']";
    private final String secondStreetField = "\'Street 2\'";
    private final String stateField = "\'State/Province\'";
    private final String homePhoneField = "\'Home\'";
    private final String workEmailField = "\'Work Email\'";
    private final String itemListXpath = "(//div[@role='listbox']//span)";

    private String xpathInputFields(String value) {
        return "//div[@class='oxd-form-row']//label[text()=" + value + "]//ancestor::div[2]//input";

    }

    private String xpathPathForItemInList(String value) {
        return "//div[@role='listbox']//span[text()=" + "\'" + value + "\'" + "]";

    }

    public void clearForm() {
        click(xpathInputFields(secondStreetField));
        clearField();
        click(xpathInputFields(stateField));
        clearField();
        click(xpathInputFields(homePhoneField));
        clearField();
        click(xpathInputFields(workEmailField));
        clearField();
    }

    public String getRandomCountryName() {
        String countryName = getRandomElement(getListValue(itemListXpath));
        return countryName;
    }

    public String fillField(String nameField, String value) {
        String newValue = value + getRandomNum();
        inputText(xpathInputFields(nameField), newValue);

        return newValue;
    }


    public void openTheItemList() {
        click(expandListCountryXpath);
    }

    public void selectCountryInList(String value) {
        click(xpathPathForItemInList(value));
    }

    public void saveForm() {
        click(saveButtonXpath);
    }

    public void waitForToastToDisappear() {
        waitForElementToDisappear(toastTextXpath, 10);
    }

    public void waitFieldNameIsPresent(String value) {
        waitTextToBePresentInElementValue(value, xpathInputFields(secondStreetField), 10);
    }

    public String getStreetName() {
        return getAttribute(xpathInputFields(secondStreetField), "value");
    }

    public String getCountryName() {
        return getElementText(selectedCountryXpath);
    }

    public String getStateName() {
        return getAttribute(xpathInputFields(stateField), "value");
    }

    public String getHomePhoneValue() {
        return getAttribute(xpathInputFields(homePhoneField), "value");
    }

    public String getEmailFieldValue() {
        return getAttribute(xpathInputFields(workEmailField), "value");
    }

}
