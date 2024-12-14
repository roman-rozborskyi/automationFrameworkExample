package com.rozborskyi.automation.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BrowserManager {
    private static final Logger LOGGER = LogManager.getLogger(BrowserManager.class);

    @Autowired
    private DriverManager driverManager;
    private WebDriver webDriver;

    public BrowserManager startBrowser() {
        LOGGER.info("Start browser");
        initDriver(driverManager);
        return this;
    }

    @Autowired
    private void initDriver(DriverManager driverManager) {
        webDriver = driverManager.getDriver();
    }

    public BrowserManager closeBrowser() {
        LOGGER.info("Close browser");
        webDriver.close();
        return this;
    }

    public BrowserManager navigateToHomePage() {
        return navigateTo("https://www.javatpoint.com/");
    }

    public BrowserManager navigateTo(String url) {
        LOGGER.info("Navigate to " + url);
        webDriver.get(url);
        return this;
    }
}
