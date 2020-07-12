import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ManagerHomePageTest extends BaseTest {
    private ManagerHomePage managerHomePage;
    private LoginPage loginPage;

    @Before
    public void initialize() {
        loginPage = new LoginPage();
        managerHomePage = new ManagerHomePage();
    }
}
