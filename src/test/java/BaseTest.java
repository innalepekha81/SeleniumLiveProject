import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseTest {
    public WebDriverManager manager;
    public WebDriver driver;
    @Before
    public void setUp() throws Exception {
        manager = WebDriverManager.getInstance();
        manager.setDriver("firefox");
        driver = manager.getCurrentDriver();
        driver.get(Util.BASE_URL);
        //loginPage = PageFactory.initElements(driver, LoginPage.class);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
