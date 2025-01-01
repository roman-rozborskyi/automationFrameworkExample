package com.rozborskyi.automation.reporter.annotationprocessors;

import com.rozborskyi.automation.reporter.Tracking;
import lombok.Getter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TrackingProcessor {
    private Tracking tracking;
    private boolean isStoryPresent;
    @Getter
    private List<String> bugs;
    @Getter
    private String story;

    public TrackingProcessor(Method method) {
        initAnnotation(method);
        initStory();
        initBugs();
        validateAnnotation();
    }

    private void validateAnnotation() {
        if (isAnnotationIncorrect(isStoryPresent, isBugsPresent())) {
            throw new RuntimeException(
                    "Annotation @Tracking must have at least one value - either story or bug(s). " +
                            "Test must have a reason of it's creation");
        }
    }

    private boolean isAnnotationIncorrect(boolean isStoryPresent, boolean isBugsPresent) {
        return !(isStoryPresent || isBugsPresent);
    }

    private void initAnnotation(Method method) {
        tracking = Optional.ofNullable(method.getAnnotation(Tracking.class))
                .orElseThrow(() -> new RuntimeException("Add \"@Tracking\" to the test"));
    }

    private void initStory() {
        story = tracking.story();
        if (story == null || story.isEmpty()) {
            story = "Story wasn't defined";
            isStoryPresent = false;
        } else {
            isStoryPresent = true;
        }
    }

    private void initBugs() {
        bugs = Arrays.asList(tracking.bugs());
    }

    public boolean isBugsPresent() {
        return !bugs.get(0).isEmpty();
    }
}