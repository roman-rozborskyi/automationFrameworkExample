package com.rozborskyi.automation;

import com.rozborskyi.automation.services.BrowserManager;
import com.rozborskyi.automation.steps.HomePageSteps;
import com.rozborskyi.automation.steps.JavaTutorialSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

@SpringBootTest(classes = UiTestsApplication.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
    @Autowired
    protected BrowserManager browserManager;
    @Autowired
    protected HomePageSteps homePageSteps;
    @Autowired
    protected JavaTutorialSteps javaTutorialSteps;

    @BeforeMethod
    public void beforeTestMethod() {
        browserManager
                .startBrowser()
                .navigateTo("https://www.javatpoint.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestMethod(Method method) {
        browserManager.closeBrowser();
    }

    @BeforeTest
    public void beforeTest(ITestContext iTestListener) {
        LOGGER.info("Start test " + iTestListener.getName());
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext iTestListener) {
        LOGGER.info("End test " + iTestListener.getName());
    }
}
