package com.rozborskyi.automation.services;

import org.springframework.stereotype.Component;

@Component
public interface BrowserManager {
    BrowserManager startBrowser();

    BrowserManager closeBrowser();

    BrowserManager navigateTo(String url);
}
