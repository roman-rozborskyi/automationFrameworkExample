package com.rozborskyi.automation.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.rozborskyi.automation.reporter.annotationprocessors.StepProcessor;
import com.rozborskyi.automation.reporter.annotationprocessors.TrackingProcessor;
import com.rozborskyi.automation.reporter.annotationprocessors.WaitForFixProcessor;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

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
        addTrackingInfo(extentTest, method);

        TESTS.set(extentTest);
    }

    private String getTestDescription(Method method) {
        Test testAnnotation = method.getAnnotation(Test.class);
        return testAnnotation.description();
    }

    private void addTrackingInfo(ExtentTest extentTest, Method method) {
        TrackingProcessor trackingProcessor = new TrackingProcessor(method);
        String story = trackingProcessor.getStory();
        List<String> bugs = trackingProcessor.getBugs();
        extentTest.info(MarkupHelper.createLabel(story, ExtentColor.BLUE));
        if (trackingProcessor.isBugsPresent()) {
            extentTest.info(MarkupHelper.createOrderedList(bugs));
        }
    }

    @Override
    public void addSuccessStep(Method method) {
        ExtentTest extentTest = TESTS.get();
        String stepDescription = new StepProcessor(method).getStepDescription();
        extentTest.createNode(stepDescription).pass("pass");
    }

    @Override
    public void addFailStep(Method method, Throwable throwable) {
        ExtentTest extentTest = TESTS.get();
        String stepDescription = new StepProcessor(method).getStepDescription();
        extentTest.createNode(stepDescription).fail(throwable, MediaEntityBuilder.createScreenCaptureFromPath(".").build());
    }

    @Override
    public void markTestFailed(Method method, Throwable throwable) {
        ExtentTest extentTest = TESTS.get();
        WaitForFixProcessor waitForFixProcessor = new WaitForFixProcessor(method);
        if (!waitForFixProcessor.isAnnotationPresent()) {
            extentTest.createNode(throwable.getMessage()).fail(throwable, MediaEntityBuilder.createScreenCaptureFromPath(".").build());
            extentTest.fail(throwable.getMessage());
        }
    }

    @Override
    public void generateReport() {
        REPORT.flush();
    }
}
