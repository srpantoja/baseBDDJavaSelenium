package managers;

import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class PageObjectManager {

    private WebDriver driver;
    private LoginPage loginPage;
    public PageObjectManager(WebDriver driver) {

        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }

    public LoginPage getLoginPage(){
        return loginPage != null? loginPage: (loginPage = new LoginPage(driver));
    }

}
