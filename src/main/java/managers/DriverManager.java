package managers;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import enums.DriverType;
import enums.EnvironmentType;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private WebDriver driver;
    private static DriverType driverType;
    private static EnvironmentType environmentType;
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public DriverManager() {
        driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
        environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {

        if(driver != null ) {
            System.out.println("share driver");
            return driver;
        }
        System.out.println("non share driver");

        switch (environmentType) {
            case LOCAL : driver = createLocalDriver();
                break;
            case REMOTE : driver = createRemoteDriver();
                break;
        }
        return driver;
    }

    private WebDriver createRemoteDriver() {
        throw new RuntimeException("RemoteWebDriver is not yet implemented");
    }

    private WebDriver createLocalDriver() {
        switch (driverType) {

            case FIREFOX :
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case CHROME :
                driver = createChromeDriver();
                break;
            case EDGE:
                driver = WebDriverManager.edgedriver().create();
                break;
            case SAFARI:
                driver = WebDriverManager.safaridriver().create();
                break;
        }
        long timeout = FileReaderManager.getInstance().getConfigReader().getImplicitlyWait();
        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        return driver;
    }

    public WebDriver createChromeDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        if(FileReaderManager.getInstance().getConfigReader().getHeadlessMode()) {
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("disable-gpu");
            chromeOptions.addArguments("--lang=pt-br");
        }

        return driver = new ChromeDriver(chromeOptions);
    }

    public void quitDriver() {
        driver.close();
        driver.quit();
    }

}