package com.rozborskyi.automation.automationFrameworkExample.pageobjects;

import org.springframework.stereotype.Component;

@Component
public class TutorialsMenu {
    public TutorialsMenu goToJavaTutorials() {
        System.out.println("click on Java menu item");
        return this;
    }
}
