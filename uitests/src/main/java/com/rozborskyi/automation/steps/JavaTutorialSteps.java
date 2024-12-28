package com.rozborskyi.automation.steps;

import com.rozborskyi.automation.pageobjects.JavaTutorialPage;
import com.rozborskyi.automation.reporter.ReporterStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JavaTutorialSteps {
    @Autowired
    private JavaTutorialPage javaTutorialPage;

    @ReporterStep(description = "Check is page \"Java Tutorial\" displayed")
    public boolean isPageDisplayed() {
        return javaTutorialPage.isPageDisplayed();
    }
}
