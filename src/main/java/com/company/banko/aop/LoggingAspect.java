package com.company.banko.aop;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class LoggingAspect {

    private static final org.apache.logging.log4j.Logger LOGGER = (org.apache.logging.log4j.Logger) LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* com.company.banko.service.FinancialAccountServiceImpl.*(..))")
    public void logBeforeAllMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" FinancialAccount Logging AOP - Display the status before executing the method" , retyrnValue);
    }

    @After("execution(* com.company.banko.service.FinancialAccountServiceImpl.*(..))")
    public void logAfterAllMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" FinancialAccount Logging AOP - Display the status after executing the method", retyrnValue);
    }


    @Around("execution(* com.company.banko.service.FinancialAccountServiceImpl.*(..))")
    public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
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

}
