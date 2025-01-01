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
public class StepAspect {

    @AfterReturning("definePointcut()")
    public void defineAdviceSuccess(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        Reporter reporter = ExtentReportsService.getInstance();
        reporter.addSuccessStep(method);
    }

    @AfterThrowing(pointcut = "definePointcut()", throwing = "throwable")
    public void defineAdviceFail(JoinPoint joinPoint, Throwable throwable) {
        Method method = getMethod(joinPoint);
        Reporter reporter = ExtentReportsService.getInstance();
        reporter.addFailStep(method, throwable);
    }

    @Pointcut("@annotation(com.rozborskyi.automation.reporter.Step) && execution(* *(..))")
    public void definePointcut() {

    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }
}
