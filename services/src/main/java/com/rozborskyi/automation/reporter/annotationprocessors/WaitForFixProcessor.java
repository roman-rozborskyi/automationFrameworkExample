package com.rozborskyi.automation.reporter.annotationprocessors;

import com.rozborskyi.automation.reporter.WaitForFix;

import java.lang.reflect.Method;
import java.util.Optional;

public class WaitForFixProcessor {
    private Optional<WaitForFix> waitForFix;

    public WaitForFixProcessor(Method method) {
        initAnnotation(method);
        if (isAnnotationPresent()) {
            validateAnnotation();
        }
    }

    private void validateAnnotation() {
        String ticket = getTicket();
        if (ticket.isEmpty()) {
            throw new RuntimeException("Annotation @WaitForFix must have value \"ticket\"");
        }
    }

    public String getTicket() {
        WaitForFix annotation = waitForFix.get();
        return annotation.ticket();
    }

    private void initAnnotation(Method method) {
        waitForFix = Optional.ofNullable(method.getAnnotation(WaitForFix.class));
    }

    public boolean isAnnotationPresent() {
        return waitForFix.isPresent();
    }
}
