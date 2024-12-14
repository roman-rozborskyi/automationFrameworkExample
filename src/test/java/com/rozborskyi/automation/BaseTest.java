package com.rozborskyi.automation;

import com.rozborskyi.automation.services.BrowserManager;
import com.rozborskyi.automation.steps.HomePageSteps;
import com.rozborskyi.automation.steps.JavaTutorialSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@SpringBootTest(classes = AutomationFrameworkExampleApplication.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    protected BrowserManager browserManager;
    @Autowired
    protected HomePageSteps homePageSteps;
    @Autowired
    protected JavaTutorialSteps javaTutorialSteps;

    @BeforeMethod
    public void startTest() {
        browserManager
                .startBrowser()
                .navigateToHomePage();
    }

    @AfterMethod
    public void endTest() {
        browserManager.closeBrowser();
    }
}
