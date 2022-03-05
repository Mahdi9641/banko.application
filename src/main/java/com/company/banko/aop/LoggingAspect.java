package com.company.banko.aop;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
public class LoggingAspect {

    private static final org.apache.logging.log4j.Logger LOGGER = (org.apache.logging.log4j.Logger) LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* com.company.banko.service.FinancialAccountServiceImpl.*(..))")
    public void logBeforeAllMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" FinancialAccount Logging AOP - Display the status before executing the method", retyrnValue);
    }

    @Before("execution(* com.company.banko.service.PersonServiceImpl.*(..))")
    public void logBeforeMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" Person Logging AOP - Display the status before executing the method", retyrnValue);
    }

    @After("execution(* com.company.banko.service.FinancialAccountServiceImpl.*(..))")
    public void logAfterAllMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" FinancialAccount Logging AOP - Display the status after executing the method", retyrnValue);
    }

    @After("execution(* com.company.banko.service.PersonServiceImpl.*(..))")
    public void logAfterMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" Person Logging AOP - Display the status after executing the method", retyrnValue);
    }


    @Around("execution(* com.company.banko.service.FinancialAccountServiceImpl.*(..))")
    public Object logFinancialAccountServiceMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        final StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();


        LOGGER.info(" FinancialAccount Logging AOP - Execution time of "
                + methodSignature.getDeclaringType().getSimpleName() // Class Name
                + "." + methodSignature.getName() + " " // Method Name
                + ":: " + stopWatch.getTotalTimeMillis() + " ms");

        return result;
    }

    @Around("execution(* com.company.banko.service.PersonServiceImpl.*(..))")
    public void logPersonServiceMethodExecutionTime() throws Throwable {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }

}
