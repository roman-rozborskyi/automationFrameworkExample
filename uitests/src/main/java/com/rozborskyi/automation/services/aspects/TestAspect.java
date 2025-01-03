package com.rozborskyi.automation.services.aspects;

import com.rozborskyi.automation.reporter.ExtentReportsService;
import com.rozborskyi.automation.reporter.Reporter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class TestAspect {

    @AfterThrowing(pointcut = "definePointcut()", throwing = "throwable")
    public void defineAdvice(JoinPoint joinPoint, Throwable throwable) {
        Reporter reporter = ExtentReportsService.getInstance();
        Method method = getMethod(joinPoint);
        reporter.markBrokenTest(method, throwable);
    }

    @AfterReturning(pointcut = "definePointcut()")
    public void defineAdvice(JoinPoint joinPoint) {
        Reporter reporter = ExtentReportsService.getInstance();
        Method method = getMethod(joinPoint);
        reporter.markWorkingTest(method);
    }

    @Pointcut("@annotation(org.testng.annotations.Test) && execution(* *(..))")
    public void definePointcut() {

    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }
}
