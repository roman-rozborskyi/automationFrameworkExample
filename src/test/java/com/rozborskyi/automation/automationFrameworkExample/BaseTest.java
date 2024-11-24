package com.rozborskyi.automation.automationFrameworkExample;

import com.rozborskyi.automation.automationFrameworkExample.steps.HomePageSteps;
import com.rozborskyi.automation.automationFrameworkExample.steps.JavaTutorialSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseTest {
    @Autowired
    protected HomePageSteps homePageSteps;
    @Autowired
    protected JavaTutorialSteps javaTutorialSteps;
}
