package com.company.banko.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.slf4j.Log4jLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    @Before("@annotation(com.company.banko.CustomAnnotation.CustomLog)")
    public void logBeforeAllMethods(final JoinPoint retyrnValue) {

        log.info(" FinancialAccount Logging AOP - Display the status before executing the method", retyrnValue);
    }

    @After("@annotation(com.company.banko.CustomAnnotation.CustomLog)")
    public void logAfterAllMethods(final JoinPoint retyrnValue) {

        log.info(" FinancialAccount Logging AOP - Display the status after executing the method", retyrnValue);
    }

    @AfterReturning(pointcut = "@annotation(com.company.banko.CustomAnnotation.CustomLog)", returning = "retVal")
    public void logAfterReturningGet(Object retVal) throws Throwable {
        if (retVal != null) {
            System.out.println("****LoggingAspect.logAfterReturning: " + ((Object) retVal).toString());
        } else {
            System.out.println("not working");
        }
    }


    @Around("@annotation(com.company.banko.CustomAnnotation.CustomLog)")
    public Object logFinancialAccountServiceMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info(" FinancialAccount Logging AOP - Execution time of "
                + methodSignature.getDeclaringType().getSimpleName() // Class Name
                + "." + methodSignature.getName() + " " // Method Name
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        Object[] signatureArgs = proceedingJoinPoint.getArgs();
        for (Object signatureArg : signatureArgs) {
            System.out.println("Arg: " + signatureArg);
        }

        return result;
    }

    @AfterThrowing(pointcut = "@annotation(com.company.banko.CustomAnnotation.CustomLog)", throwing = "ex")
    public void logAfterThrowingAllMethods(Exception ex) throws Throwable {
        log.info("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
    }
}
