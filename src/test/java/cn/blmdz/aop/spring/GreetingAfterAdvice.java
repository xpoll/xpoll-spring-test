package cn.blmdz.aop.spring;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * 后置增强
 * @date 2017年8月31日
 */
public class GreetingAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		String name = (String) args[0];
		System.out.println("bye, " + name + "!");
	}

}
