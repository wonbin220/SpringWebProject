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

@Component//스프링의 bean으로 인식되기위해 설정
@Aspect//AOP 기능을 하는 클래스의 선언에 추가
public class SampleAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	@Before("execution(* com.springtest.service.MessageService*.*(..))")
	// 해당 메소드를 먼저 실행한 후 target 메소드가 실행, execution:Pointcut을 지정하는 문법(AspectJ 문법을 사용)
	// com.springtest.service.MessageService로 시작하는 모든 클래스의 *(모든)메소드를 지정
	public void startLog(JoinPoint jp) {
		
		logger.info("-before--------------------");		
		logger.info(Arrays.toString(jp.getArgs()));
		logger.info("--------------------------");
	}
	
	// Around를 이용할경우 반드시 메소드의 리턴 타입은 Object로 선언, 메소드를 직접 호출하고 
	// 결과를 반환해야만 정상적인 처리가 됨
	@Around("execution(* com.springtest.service.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		logger.info("-around-------------------------");	
		logger.info(Arrays.toString(pjp.getArgs()));
		logger.info("--------------------------");	
		
		Object result = pjp.proceed(); // proceed를 이용해 실제 메소드를 호출
		// Object proceed() = 다음 Advice를 실행하거나, 실제 target 객체의 메소드를 실행하는 기능
		
		long endTime = System.currentTimeMillis();
		logger.info("=around===========================================");
		logger.info(pjp.getSignature().getName() + " : " + (endTime - startTime));
		logger.info("============================================");
		
		return result;
	}
}
