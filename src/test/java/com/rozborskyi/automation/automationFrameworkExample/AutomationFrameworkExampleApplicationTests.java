package com.rozborskyi.automation.automationFrameworkExample;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AutomationFrameworkExampleApplicationTests extends BaseTest {

    @Test
    void contextLoads() {
        homePageSteps.goToJavaTutorial();
        boolean isPageDisplayed = javaTutorialSteps.isPageDisplayed();
        Assertions.assertTrue(isPageDisplayed, "Page \"Java tutorial\" isn't displayed");
    }
}
