import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public BasePage() {
        WebDriver driver = WebDriverManager.getInstance().getCurrentDriver();
        PageFactory.initElements(driver, this);
    }
}
