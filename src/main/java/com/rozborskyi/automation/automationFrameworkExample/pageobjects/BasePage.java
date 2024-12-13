package com.rozborskyi.automation.automationFrameworkExample.pageobjects;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

@Component
public abstract class BasePage {
    private WebDriver webDriver;

    protected WebDriver getWebDriver() {
        return webDriver;
    }
}
