package pages;

import dataProviders.WaitProviders;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.*;

public class GenericPage {

    @FindAll(@FindBy(how = How.CSS, using = ".alert"))
    private List<WebElement> toast_message;
    private WaitProviders wait;

    public GenericPage(WebDriver driver){
        this.wait = new WaitProviders(driver);
    }

    public List<String> getToastMessage(){
        List<String> listToastMessages = new ArrayList<String>();
        for (WebElement msg: toast_message) {
            listToastMessages.add(msg.getText());
        }
        return listToastMessages;
    }


}
