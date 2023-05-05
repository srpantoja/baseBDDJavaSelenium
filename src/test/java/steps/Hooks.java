package steps;

import cucumber.TestContext;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

public class Hooks {

    static TestContext testContext;
    static WebDriver driver;

    public Hooks(TestContext context) {
        testContext = context;
    }

    @Before
    public void BeforeSteps() {
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
    }

    @After
    public void AfterSteps() {
        if(testContext != null)
            testContext.getWebDriverManager().quitDriver();
    }

}