package com.rozborskyi.automation.automationFrameworkExample.pageobjects;

import org.springframework.stereotype.Component;

@Component
public class HomePage {

    public HomePage openTutorialsMenu() {
        System.out.println("open tutorials menu");
        return this;
    }
}
