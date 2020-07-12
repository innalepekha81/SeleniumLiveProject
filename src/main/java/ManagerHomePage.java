import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagerHomePage extends BasePage{
    //private WebDriver driver;
    static JavascriptExecutor js;

   // public ManagerHomePage(WebDriver driver) {
  //      this.driver=driver;
  //  }
    @FindBy(xpath = "//tr[@class='heading3']//td[contains(text(),'Manger Id')]")
    private WebElement isManager;

    @FindBy(xpath = "//a[text()='Log out']")
    private WebElement logOut;

    public String managerText(){
         return isManager.getText().substring(0,11);

    }

    public String titleHomePage () { return  WebDriverManager.getInstance().getCurrentDriver().getTitle();

    }
    public void logOut(){
        js = (JavascriptExecutor)  WebDriverManager.getInstance().getCurrentDriver();
        js.executeScript("arguments[0].scrollIntoView();", logOut);
        logOut.click();

    }

}


