package com.rozborskyi.automation.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManagerImpl implements DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();
    private static final Logger LOGGER = LogManager.getLogger(DriverManagerImpl.class);

    public DriverManagerImpl() {
        WebDriver webDriver = initDriver();
        DRIVER_THREAD_LOCAL.set(webDriver);
    }

    private WebDriver initDriver() {
        LOGGER.info("Instantiate ChromeDriver");
        return new ChromeDriver();
    }

    public WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }
}
