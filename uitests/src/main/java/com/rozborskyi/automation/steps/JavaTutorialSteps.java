package com.rozborskyi.automation.steps;

import com.rozborskyi.automation.pageobjects.JavaTutorialPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JavaTutorialSteps {
    @Autowired
    private JavaTutorialPage javaTutorialPage;

    public boolean isPageDisplayed() {
        return javaTutorialPage.isPageDisplayed();
    }
}
