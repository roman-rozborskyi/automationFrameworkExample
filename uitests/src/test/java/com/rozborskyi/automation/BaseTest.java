package com.rozborskyi.automation;

import com.rozborskyi.automation.reporter.ExtentReportsService;
import com.rozborskyi.automation.reporter.Reporter;
import com.rozborskyi.automation.services.BrowserManager;
import com.rozborskyi.automation.steps.HomePageSteps;
import com.rozborskyi.automation.steps.JavaTutorialSteps;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@SpringBootTest(classes = UiTestsApplication.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
    @Autowired
    private BrowserManager browserManager;
    protected Reporter reporter = ExtentReportsService.getInstance();
    @Autowired
    protected HomePageSteps homePageSteps;
    @Autowired
    protected JavaTutorialSteps javaTutorialSteps;

    @BeforeMethod
    public void beforeTestMethod(Method method) {
        reporter.addTest(method);
        browserManager
                .startBrowser()
                .navigateTo("https://docs.oracle.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestMethod() {
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

    @AfterSuite
    public void afterSuit() {
        reporter.generateReport();
    }
}
