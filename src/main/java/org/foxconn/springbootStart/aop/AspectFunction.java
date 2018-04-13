package org.foxconn.springbootStart.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Aspect
@Order
public class AspectFunction {
	Logger logger =Logger.getLogger(AspectFunction.class);
	
	@Pointcut("@annotation(org.foxconn.springbootStart.DataSourceAspect)")
//	@Pointcut("execution(* org.foxconn.springbootStart..*(..))")''
	public void aspect(){
	
		logger.info("aspect....");
	}
	@Before("aspect()")
	public void doBefore(JoinPoint joinpoint){ 
		Signature signature  =joinpoint.getSignature();
		logger.info("before aspect...."  );
		logger.info("functionName...."+signature.getName());
	}
	@After("aspect()")
	public void doAfter(){
		logger.info("after aspect....");
	}
}
