package com.rozborskyi.automation.reporter;

import java.lang.reflect.Method;

public interface Reporter {
    void addTest(Method method);

    void addWorkingStep(Method method);

    void addBrokenStep(Method method, Throwable throwable);

    void markBrokenTest(Method method, Throwable throwable);

    void markWorkingTest(Method method);

    void generateReport();
}
