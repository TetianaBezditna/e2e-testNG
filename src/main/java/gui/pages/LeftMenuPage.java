package gui.pages;

public class LeftMenuPage extends BasePage {
    private final String myInfoCategory = "//ul[@class='oxd-main-menu']//span[text()='My Info']";

    public void openTheMyInfoCategory() {
        click(myInfoCategory);

    }
}
