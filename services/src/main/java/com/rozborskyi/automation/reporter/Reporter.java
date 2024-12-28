package com.rozborskyi.automation.reporter;

import java.lang.reflect.Method;

public interface Reporter {
    void addTest(Method method);

    void addSuccessStep(String stepDescription);

    void addFailStep(String stepDescription, Throwable throwable);

    void generateReport();
}
