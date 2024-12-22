package com.rozborskyi.automation.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public class BrowserManagerImp implements BrowserManager {
    private static final Logger LOGGER = LogManager.getLogger(BrowserManagerImp.class);
    private WebDriver webDriver;

    public BrowserManagerImp startBrowser() {
        LOGGER.info("Start browser");
        DriverManager driverManager = new DriverManagerImpl();
        webDriver = driverManager.getDriver();
        return this;
    }

    public BrowserManagerImp closeBrowser() {
        LOGGER.info("Close browser");
        webDriver.quit();
        return this;
    }

    public BrowserManagerImp navigateTo(String url) {
        LOGGER.info("Navigate to " + url);
        webDriver.get(url);
        return this;
    }
}
