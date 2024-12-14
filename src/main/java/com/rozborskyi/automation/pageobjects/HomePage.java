package com.rozborskyi.automation.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(HomePage.class);

    public HomePage openTutorialsMenu() {
        LOGGER.info("open tutorials menu");
        return this;
    }
}
