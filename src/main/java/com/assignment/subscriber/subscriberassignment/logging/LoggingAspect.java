package com.assignment.subscriber.subscriberassignment.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    //flag for test cases
    private boolean isAspectCalled;

    public void setAspectCalled(boolean aspectCalled) {
        isAspectCalled = aspectCalled;
    }

    public boolean isAspectCalled() {
        return isAspectCalled;
    }

    @Before(value = "controllerAllMethods() && requestMappingPointCut(requestMapping)", argNames = "joinPoint,requestMapping")
    public void allMethods(JoinPoint joinPoint, RequestMapping requestMapping) {
        Object[] args = joinPoint.getArgs();
        String[] url = requestMapping.value();
        RequestMethod[] methods = requestMapping.method();

        log.info(String.format("%s %s %s", Arrays.toString(url), Arrays.toString(methods), Arrays.toString(args)));
        isAspectCalled = true;
    }

    @Pointcut("execution(* com.assignment.subscriber.subscriberassignment.controller..*(..)))")
    protected void controllerAllMethods() {
    }

    @Pointcut("@annotation(requestMapping)")
    protected void requestMappingPointCut(RequestMapping requestMapping) {
    }
}
