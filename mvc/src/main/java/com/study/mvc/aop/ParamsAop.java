package com.study.mvc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
public class ParamsAop {
    @Pointcut("@annotation(com.study.mvc.aop.annotation.ParamsAspect)")
    private void pointCut() {}
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();

        CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
        String[] paramsName = codeSignature.getParameterNames();

        String className = codeSignature.getDeclaringTypeName(); // 클래스 이름
        String methodName = codeSignature.getName(); // 매소드 이름

        for(int i = 0; i < args.length; i++) {
            log.info("{}.{} >>> {} : {}", className, methodName, paramsName[i], args[i]);
        }

        Object result = proceedingJoinPoint.proceed(); // 비즈니스 로직

        return result;
    }
}
