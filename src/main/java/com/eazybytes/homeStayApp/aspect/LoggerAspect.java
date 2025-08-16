package com.eazybytes.homeStayApp.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Aspect
@Slf4j
public class LoggerAspect {

    @Around("execution(* com.eazybytes..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable{

        log.info("{} method execution starts", joinPoint.getSignature().toString());
        Instant start = Instant.now();
        Object returnObj = joinPoint.proceed();
        Instant finish = Instant.now();
        long duration = Duration.between(start,finish).toMillis();
        log.info("Time taken for execution = {}", duration);
        log.info("{} method execution ends",joinPoint.getSignature().toString());
        return returnObj;


    }

    @AfterThrowing(value = "execution(* com.eazybytes.*.*(..))",throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error(joinPoint.getSignature()+ " An exception happened due to : "+ex.getMessage());
    }
}
