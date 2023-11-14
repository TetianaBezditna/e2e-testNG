package gui.pages;

public class MyInfoPage extends BasePage{
    private final String contactDetails = "//div[@class='orangehrm-edit-employee-navigation']//a[text()='Contact Details']";
    public void openContactDetailsCategory(){
        click(contactDetails);

    }
}
