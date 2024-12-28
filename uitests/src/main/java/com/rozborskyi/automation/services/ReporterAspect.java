package com.rozborskyi.automation.services;

import com.rozborskyi.automation.reporter.ExtentReportsService;
import com.rozborskyi.automation.reporter.Reporter;
import com.rozborskyi.automation.reporter.ReporterStep;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class ReporterAspect {

    @Before("definePointcut()")
    public void defineAdvice(JoinPoint joinPoint) {
        String description = getStepDescription(joinPoint);
        Reporter reporter = ExtentReportsService.getInstance();
        reporter.addSuccessStep(description);
    }

    @Pointcut("@annotation(com.rozborskyi.automation.reporter.ReporterStep) && execution(* *(..))")
    public void definePointcut() {

    }

    private String getStepDescription(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        ReporterStep annotation = method.getAnnotation(ReporterStep.class);
        return annotation.description();
    }
}
