package com.rozborskyi.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SomeService {
    private static final Logger LOGGER = LogManager.getLogger(SomeService.class);

    public void doSmth() {
        LOGGER.info("service method invocation");
    }
}
