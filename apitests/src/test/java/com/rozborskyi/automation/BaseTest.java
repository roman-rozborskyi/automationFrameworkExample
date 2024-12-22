package com.rozborskyi.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

@SpringBootTest(classes = ApiTestsApplication.class)
public abstract class BaseTest extends AbstractTestNGSpringContextTests {
    private static final Logger LOGGER = LogManager.getLogger(BaseTest.class);

    @BeforeTest
    public void beforeTest(ITestContext iTestListener) {
        LOGGER.info("Start test " + iTestListener.getName());
    }

    @AfterTest(alwaysRun = true)
    public void afterTest(ITestContext iTestListener) {
        LOGGER.info("End test " + iTestListener.getName());
    }
}
