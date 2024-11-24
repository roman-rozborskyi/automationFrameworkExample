package com.rozborskyi.automation.automationFrameworkExample.pageobjects;

import org.springframework.stereotype.Component;

@Component
public class JavaTutorialPage {
    public boolean isPageDisplayed() {
        System.out.println("java tutorial page is displayed");
        return true;
    }
}
