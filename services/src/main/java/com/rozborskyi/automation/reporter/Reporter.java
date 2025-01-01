package com.rozborskyi.automation.reporter;

import java.lang.reflect.Method;

public interface Reporter {
    void addTest(Method method);

    void addSuccessStep(Method method);

    void addFailStep(Method method, Throwable throwable);

    void markTestFailed(Method method, Throwable throwable);

    void generateReport();
}
