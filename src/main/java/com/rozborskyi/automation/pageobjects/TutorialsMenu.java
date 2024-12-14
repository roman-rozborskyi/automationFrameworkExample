package com.rozborskyi.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TutorialsMenu extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(TutorialsMenu.class);

    public TutorialsMenu goToJavaTutorials() {
        LOGGER.info("click on Java menu item");
        return this;
    }
}
