package com.rozborskyi.automation.automationFrameworkExample;

import com.rozborskyi.automation.automationFrameworkExample.services.BrowserManager;
import com.rozborskyi.automation.automationFrameworkExample.steps.HomePageSteps;
import com.rozborskyi.automation.automationFrameworkExample.steps.JavaTutorialSteps;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class BaseTest {
    @Autowired
    protected BrowserManager browserManager;
    @Autowired
    protected HomePageSteps homePageSteps;
    @Autowired
    protected JavaTutorialSteps javaTutorialSteps;

    @BeforeEach
    public void startTest() {
        browserManager
                .startBrowser()
                .navigateToHomePage();
    }

    @AfterEach
    public void endTest() {
        browserManager.closeBrowser();
    }
}
