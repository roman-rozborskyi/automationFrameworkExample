package com.rozborskyi.automation.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public final class ExtentReportsService implements Reporter {
    private static ExtentReportsService extentReportsService;
    private static final String PATH_REPORT = "target/report.html";
    private static final ExtentReports REPORT = new ExtentReports();
    private static final ThreadLocal<ExtentTest> TESTS = new ThreadLocal<>();

    private ExtentReportsService() {
        ExtentSparkReporter htmlObserver = new ExtentSparkReporter(PATH_REPORT);
        REPORT.attachReporter(htmlObserver);
    }

    public static synchronized ExtentReportsService getInstance() {
        if (extentReportsService == null) {
            extentReportsService = new ExtentReportsService();
        }
        return extentReportsService;
    }

    @Override
    public void addTest(Method method) {
        String description = getTestDescription(method);
        ExtentTest extentTest = REPORT.createTest(description);
        TESTS.set(extentTest);
    }

    private String getTestDescription(Method method) {
        Annotation testAnnotation = method.getAnnotation(Test.class);
        return ((Test) testAnnotation).description();
    }

    @Override
    public void addSuccessStep(String stepDescription) {
        ExtentTest extentTest = TESTS.get();
        extentTest.createNode(stepDescription).pass("pass");
    }

    @Override
    public void addFailStep(String stepDescription, Throwable throwable) {
        ExtentTest extentTest = TESTS.get();
        Media screenshot = MediaEntityBuilder.createScreenCaptureFromPath("img.jpeg").build();
        extentTest.createNode(stepDescription).fail(throwable, screenshot);
    }

    @Override
    public void generateReport() {
        REPORT.flush();
    }
}
