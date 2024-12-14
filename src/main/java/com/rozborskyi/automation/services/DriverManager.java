package com.rozborskyi.automation.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Component;

@Component
public class DriverManager {
    private final ThreadLocal<WebDriver> webDriverContainer = new ThreadLocal<>();

    public WebDriver getDriver() {
        WebDriver webDriver = new ChromeDriver();
        webDriverContainer.set(webDriver);
        return webDriverContainer.get();
    }
}
