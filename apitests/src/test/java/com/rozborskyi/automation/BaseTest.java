package com.rozborskyi.automation;

import com.rozborskyi.automation.reporter.ExtentReportsService;
import com.rozborskyi.automation.reporter.Reporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

@SpringBootTest(classes = ApiTestsApplication.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);
    private Reporter reporter = ExtentReportsService.getInstance();

    @BeforeMethod
    public void beforeTestMethod(Method method) {
        reporter.addTest(method);
    }

    @BeforeTest
    public void beforeTest(ITestContext iTestListener) {
        LOGGER.info("Start test " + iTestListener.getName());
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext iTestListener) {
        LOGGER.info("End test " + iTestListener.getName());
        reporter.generateReport();
    }
}
