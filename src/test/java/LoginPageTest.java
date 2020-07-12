import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



import java.util.List;


public class LoginPageTest extends BaseTest {
    private LoginPage loginPage;
    private ManagerHomePage managerHomePage;
    private List<String> testData;


    @Before
    public void initialize() {
        loginPage = new LoginPage();
        managerHomePage = new ManagerHomePage();

    }

    @Test
    //Login with correct and incorrect credentials
    public void loginWithCredentials() throws Exception {
        testData = ReadDataFromExcel.readExcel(Util.FILE_PATH,
                Util.TABLE_NAME, Util.SHEET_NAME);
        for (int i = 0; i < testData.size(); i += 2) {
            String usernameValue = testData.get(i); // get username
            String passwordValue = testData.get(i + 1);// get password
            WebDriverManager.getInstance().getCurrentDriver().get(Util.BASE_URL);

            loginPage.enterCredentials(usernameValue, passwordValue);
            String errorText = loginPage.getErrorInPopup();
            if (errorText == null) {
                String managerText = managerHomePage.managerText();
                Assert.assertEquals(Util.PATTERN_MANAGER + " " + usernameValue, managerText + " " + usernameValue);
                String titleHomePage = managerHomePage.titleHomePage();
                Assert.assertEquals(Util.TITLE_TEXT, titleHomePage);
            } else {
                Assert.assertEquals(Util.ERROR_Text, errorText);
                loginPage.clickOnOk();
            }

        }

    }

}

