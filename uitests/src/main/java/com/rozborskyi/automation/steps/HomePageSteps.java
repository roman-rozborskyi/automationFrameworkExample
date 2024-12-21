package com.rozborskyi.automation.steps;

import com.rozborskyi.automation.pageobjects.HomePage;
import com.rozborskyi.automation.pageobjects.TutorialsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePageSteps {
    @Autowired
    private HomePage homePage;
    @Autowired
    private TutorialsMenu tutorialsMenu;

    public HomePageSteps goToJavaTutorial() {
        homePage.openTutorialsMenu();
        tutorialsMenu.goToJavaTutorials();
        return this;
    }
}
