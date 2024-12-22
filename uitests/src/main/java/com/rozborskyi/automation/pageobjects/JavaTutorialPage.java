package com.rozborskyi.automation.pageobjects;

import com.rozborskyi.automation.SomeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class JavaTutorialPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(JavaTutorialPage.class);

    public boolean isPageDisplayed() {
        new SomeService().doSmth();
        LOGGER.info("java tutorial page is displayed");
        return true;
    }
}
