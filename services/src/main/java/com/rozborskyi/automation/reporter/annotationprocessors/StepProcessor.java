package com.rozborskyi.automation.reporter.annotationprocessors;

import com.rozborskyi.automation.reporter.Step;

import java.lang.reflect.Method;

public class StepProcessor {
    private final Method method;

    public StepProcessor(Method method) {
        this.method = method;
    }

    public String getStepDescription() {
        Step annotation = method.getAnnotation(Step.class);
        return annotation.description();
    }
}
