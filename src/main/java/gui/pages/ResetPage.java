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

    public String getFormTitle(){
        return getElementText(titleResetForm);
    }

    public String getDescription(){
        return   getElementText(descriptionFormXpath);
    }

    public String getNameInModalWindow(){
        return getElementText(fieldNameXpath);
    }

    public String getPlaceholder (){
        return getAttribute(inputFieldXpath, "placeholder");
    }

    public Boolean getCancelButton(){
        return getElementVisibilityResult(cancelButtonXpath);
    }

    public Boolean getResetPasswordButton(){
        return getElementVisibilityResult(resetPasswordButtonXpath);
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

    public String getInfoInTheForm(int a){
        return getElementText(xpathDescription(a));
    }
    public String getValidationMessage(){
        return getElementText(validationMessage);
    }

}
