package com.springtest.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SampleAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	@Before("execution(* com.springtest.service.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		
		logger.info("--------------------------");
		logger.info("--------------------------");
		logger.info(Arrays.toString(jp.getArgs()));
	}
	
	// Around를 이용할경우 반드시 메소드의 리턴 타입은 Object로 선언, 메소드를 직접 호출하고 
	// 결과를 반환해야만 정상적인 처리가 됨
	@Around("execution(* com.springtest.service.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		logger.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed(); // proceed를 이용해 실제 메소드를 호출
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		logger.info("============================================");
		
		return result;
	}
}
