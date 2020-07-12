import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverManager {
    // local variables
    private static WebDriverManager instance = null;
    private static final int IMPLICIT_TIMEOUT = 0;

    private ThreadLocal<WebDriver> webDriver =
            new ThreadLocal<WebDriver>();


    // constructor
    private WebDriverManager() {
    }

    /**
     * getInstance method to retrieve active driver instance
     *
     * @return driver.CreateDriver
     */
    public static WebDriverManager getInstance() {
        if ( instance == null ) {
            instance = new WebDriverManager();
        }

        return instance;
    }
    /**
     * setDriver method
     *
     * @param browser
     * @throws Exception
     */
    //   @SafeVarargs
    public final void setDriver(String browser)

            throws Exception {

        DesiredCapabilities caps = null;
        switch (browser) {
            case "firefox":
                caps = DesiredCapabilities.firefox();

                FirefoxOptions ffOpts = new FirefoxOptions();
                FirefoxProfile ffProfile = new FirefoxProfile();

                ffProfile.setPreference("browser.autofocus", true);
                ffProfile.setPreference("browser.tabs.remote.autostart.2", false);

                caps.setCapability(FirefoxDriver.PROFILE, ffProfile);
                caps.setCapability("marionette", true);

                System.setProperty("webdriver.gecko.driver", "D:\\Autotesting\\Real Tests\\Maven Project\\drivers\\geckodriver.exe");
                webDriver.set(new FirefoxDriver(ffOpts.merge(caps)));

                break;

            case "chrome":
                caps = DesiredCapabilities.chrome();

                ChromeOptions chOptions = new ChromeOptions();
                Map<String, Object> chromePrefs = new HashMap<String, Object>();

                chromePrefs.put("credentials_enable_service", false);

                chOptions.setExperimentalOption("prefs", chromePrefs);
                chOptions.addArguments("--disable-plugins", "--disable-extensions", "--disable-popup-blocking");

                caps.setCapability(ChromeOptions.CAPABILITY, chOptions);
                caps.setCapability("applicationCacheEnabled", false);


                System.setProperty("webdriver.chrome.driver", "D:\\Autotesting\\Real Tests\\Maven Project\\drivers\\chromedriver.exe");
                webDriver.set(new ChromeDriver(chOptions.merge(caps)));


                break;

        }

        getDriver().manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        getDriver().manage().window().maximize();
    }

    /**
     * getDriver method will retrieve the active WebDriver
     *
     * @return WebDriver
     */
    protected WebDriver getDriver() {
        return webDriver.get();
    }

    /**
     * getCurrentDriver method will retrieve the active WebDriver
     * @return WebDriver
     */

    public WebDriver getCurrentDriver() {

        return getInstance().getDriver();

    }

    /**
     * closeDriver method quits the current active driver
     */
    public void closeDriver() {
        try {
            getCurrentDriver().quit();
        }

        catch ( Exception e ) {
            // do something here I will do log file
        }
    }
}
