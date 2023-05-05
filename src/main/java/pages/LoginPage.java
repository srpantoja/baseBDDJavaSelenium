package pages;

import dataProviders.Constants;
import dataProviders.WaitProviders;
import managers.FileReaderManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
public class LoginPage extends GenericPage{
    private WaitProviders wait;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.wait = new WaitProviders(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "email")
    private WebElement input_email;
    @FindBy(how = How.ID, using = "senha")
    private WebElement input_password;

    @FindBy(how = How.XPATH, using = "//button[text()='Entrar']")
    private WebElement btn_login;

    public void enterEmail(String email){
        wait.waitElementIsVisible(input_email);
        input_email.sendKeys(email);
    }

    public void enterPassword(String password){
        wait.waitElementIsVisible(input_password);
        input_password.sendKeys(password);
    }

    public void clickOnBtnLogin(){
        wait.waitElementIsClickable(btn_login);
        btn_login.click();
    }

    public void loggedPageIsLoaded(){
        wait.waitPageIsLoaded(Constants.BASE_URL + Constants.LOGGED_URL);
    }

    public String getValidationMessage(){
        return input_email.getAttribute("validationMessage");
    }
}
