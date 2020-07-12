import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    @FindBy(xpath = "//input[@name='uid']")
    private WebElement userID;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement userPassword;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@type='reset']")
    private WebElement resetButton;


    public void enterUserName(String userName){
        userID.sendKeys(userName);

    }
    public void enterPassword(String password){
        userPassword.sendKeys(password);

    }
    public void clickOnLoginButton(){
        loginButton.click();

    }
    public void clickOnResetButton(){
        resetButton.click();

    }
    public ManagerHomePage enterCredentials(String userName, String password){
        enterUserName(userName);
        enterPassword(password);
        clickOnLoginButton();
        return new ManagerHomePage();
    }
    public String getErrorInPopup(){
        String errorText = null;
        try{
            errorText = WebDriverManager.getInstance().getCurrentDriver().switchTo().alert().getText();}
        catch (NoAlertPresentException Ex){
            errorText=null;
        }
        return errorText;
    }


    public void clickOnOk(){
        WebDriverManager.getInstance().getCurrentDriver().switchTo().alert().accept();
    }


}
