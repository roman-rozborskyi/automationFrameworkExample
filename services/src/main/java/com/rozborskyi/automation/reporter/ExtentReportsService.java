package com.rozborskyi.automation.reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        new TrackingProcessor().addInfoToTest(extentTest, method);
        TESTS.set(extentTest);
    }

    private String getTestDescription(Method method) {
        Test testAnnotation = method.getAnnotation(Test.class);
        return testAnnotation.description();
    }

    @Override
    public void addSuccessStep(String stepDescription) {
        ExtentTest extentTest = TESTS.get();
        extentTest.createNode(stepDescription).pass("pass");
    }

    @Override
    public void addFailStep(String stepDescription, Throwable throwable) {
        ExtentTest extentTest = TESTS.get();
        extentTest.createNode(stepDescription).fail(throwable, MediaEntityBuilder.createScreenCaptureFromPath(".").build());
    }

    @Override
    public void markTestFailed(Throwable throwable) {
        ExtentTest extentTest = TESTS.get();
        extentTest.createNode(throwable.getMessage()).fail(throwable, MediaEntityBuilder.createScreenCaptureFromPath(".").build());
        extentTest.fail(throwable.getMessage());
    }

    @Override
    public void generateReport() {
        REPORT.flush();
    }

    private class TrackingProcessor {
        private void addInfoToTest(ExtentTest extentTest, Method method) {
            Tracking annotation = getAnnotationIfPresent(method);
            boolean isStoryPresent = addStory(extentTest, annotation);
            boolean isBugsPresent = addBugs(extentTest, annotation);
            if (isAnnotationIncorrect(isStoryPresent, isBugsPresent)) {
                throw new RuntimeException(
                        "Annotation @Tracking must have at least one value - either story or bug(s). " +
                                "Test must have a reason of it's creation");
            }
        }

        private boolean isAnnotationIncorrect(boolean isStoryPresent, boolean isBugsPresent) {
            return !(isStoryPresent || isBugsPresent);
        }

        private Tracking getAnnotationIfPresent(Method method) {
            return Optional.ofNullable(method.getAnnotation(Tracking.class))
                    .orElseThrow(() -> new RuntimeException(String.format("Add \"@Tracking\" to the test %s", getTestDescription(method))));
        }

        private boolean addStory(ExtentTest extentTest, Tracking tracking) {
            String story = tracking.story();
            boolean isStoryPresent = true;

            if (story == null || story.isEmpty()) {
                story = "Story wasn't defined";
                isStoryPresent = false;
            }
            extentTest.info(MarkupHelper.createLabel(story, ExtentColor.BLUE));

            return isStoryPresent;
        }

        private boolean addBugs(ExtentTest extentTest, Tracking tracking) {
            List<String> bugs = Arrays.asList(tracking.bugs());
            if (isBugsPresent(bugs)) {
                extentTest.info(MarkupHelper.createOrderedList(bugs));
                return true;
            } else {
                return false;
            }
        }

        private boolean isBugsPresent(List<String> bugs) {
            return !bugs.get(0).isEmpty();
        }
    }
}
