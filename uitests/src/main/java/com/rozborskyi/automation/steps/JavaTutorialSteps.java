package com.rozborskyi.automation.steps;

import com.rozborskyi.automation.pageobjects.JavaTutorialPage;
import com.rozborskyi.automation.reporter.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JavaTutorialSteps {
    @Autowired
    private JavaTutorialPage javaTutorialPage;

    @Step(description = "Check is page \"Java Tutorial\" displayed")
    public boolean isPageDisplayed() {
        return javaTutorialPage.isPageDisplayed();
    }
}
