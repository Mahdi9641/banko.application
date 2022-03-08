package com.company.banko.aop;

import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingAspect {

    private static final org.apache.logging.log4j.Logger LOGGER = (org.apache.logging.log4j.Logger) LogManager.getLogger(LoggingAspect.class);

    @Before("execution(* com.company.banko.service.impl.FinancialAccountServiceImpl.*(..))")
    public void logBeforeAllMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" FinancialAccount Logging AOP - Display the status before executing the method", retyrnValue);
    }

    @Before("execution(* com.company.banko.service.impl.PersonServiceImpl.*(..))")
    public void logBeforeMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" Person Logging AOP - Display the status before executing the method", retyrnValue);
    }

    @After("execution(* com.company.banko.service.impl.FinancialAccountServiceImpl.*(..))")
    public void logAfterAllMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" FinancialAccount Logging AOP - Display the status after executing the method", retyrnValue);
    }

    @After("execution(* com.company.banko.service.impl.PersonServiceImpl.*(..))")
    public void logAfterMethods(final JoinPoint retyrnValue) {

        LOGGER.info(" Person Logging AOP - Display the status after executing the method", retyrnValue);
    }


    // @Around("execution(* com.company.banko.service.impl.FinancialAccountServiceImpl.*(..))")
    @Around("@annotation(com.company.banko.CustomAnnotation.CustomLog)")
    public Object logFinancialAccountServiceMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

/*        if (methodSignature == null) {
            throw new Exception("The method does not work");
        }*/
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        LOGGER.info(" FinancialAccount Logging AOP - Execution time of "
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
        LOGGER.info("****LoggingAspect.logAfterThrowingAllMethods() " + ex);
    }
}
