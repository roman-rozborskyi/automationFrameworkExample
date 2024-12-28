package com.rozborskyi.automation.services;

import com.rozborskyi.automation.reporter.ExtentReportsService;
import com.rozborskyi.automation.reporter.Reporter;
import com.rozborskyi.automation.reporter.Step;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class ReporterAspect {

    @AfterReturning("definePointcut()")
    public void defineAdviceSuccess(JoinPoint joinPoint) {
        String description = getStepDescription(joinPoint);
        Reporter reporter = ExtentReportsService.getInstance();
        reporter.addSuccessStep(description);
    }

    @AfterThrowing(pointcut = "definePointcut()", throwing = "throwable")
    public void defineAdviceFail(JoinPoint joinPoint, Throwable throwable) {
        String description = getStepDescription(joinPoint);
        Reporter reporter = ExtentReportsService.getInstance();
        reporter.addFailStep(description, throwable);
    }

    @Pointcut("@annotation(com.rozborskyi.automation.reporter.Step) && execution(* *(..))")
    public void definePointcut() {

    }

    private String getStepDescription(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Step annotation = method.getAnnotation(Step.class);
        return annotation.description();
    }
}
