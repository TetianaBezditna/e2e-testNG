package gui.pages;
public class ResetPage extends BasePage{

    private final String resetPasswordButtonXpath = "//form[@class='oxd-form']//button[text()=' Reset Password ']";
    private  final String cancelButtonXpath = "//form[@class='oxd-form']//button[text()=' Cancel ']";
    private final String inputFieldXpath = "(//form[@class='oxd-form']//input)[2]";
    final String titleResetForm = "//div[@id='app']//h6";
    private final String descriptionFormXpath = "(//form[@class='oxd-form']//p)[2]";
    private final String fieldNameXpath = "//form[@class='oxd-form']//label";
    final String validationMessage = "//div[@class='oxd-form-row']//span";
    private String xpathDescription (int a ) {
        return  "(//div[@id='app']//p)" + "[" + a + "]";
    }

    public void checkFormTitle(String expectedValue){
        checkText(expectedValue, titleResetForm);
    }

    public void checkDescription(String expectedValue){
        checkText(expectedValue, descriptionFormXpath);
    }

    public void checkNameInModalWindow (String expectedValue){
        checkText(expectedValue, fieldNameXpath);
    }

    public void checkPlaceholder (String expectedValue){
        checkAttributes(expectedValue, inputFieldXpath, "placeholder");
    }

    public void checkCancelButton(){
        checkElementIsDisplayed(cancelButtonXpath);
    }

    public void checkResetPasswordButton(){
        checkElementIsDisplayed(resetPasswordButtonXpath);
    }

    public void fillResetPasswordForm(String value){
        inputText(inputFieldXpath, value);
    }

    public void clickResetPasswordButton(){
        click(resetPasswordButtonXpath);
    }
    public void clickCancelButton(){
        click(cancelButtonXpath);
    }

    public void checkInfoInTheForm(String expectedValue, int a){
        checkText(expectedValue, xpathDescription(a));
    }
    public void checkValidationMessage(String expectedValue){
        checkText(expectedValue, validationMessage);
    }

}
