package com.rozborskyi.automation.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class DriverManager {
    private final ThreadLocal<WebDriver> webDriverContainer = new ThreadLocal<>();

    public DriverManager() {
        WebDriver webDriver = getWebDriver();
        webDriverContainer.set(webDriver);
    }

    private WebDriver getWebDriver() {
        return new ChromeDriver();
    }

    public WebDriver getDriver() {
        return webDriverContainer.get();
    }
}
