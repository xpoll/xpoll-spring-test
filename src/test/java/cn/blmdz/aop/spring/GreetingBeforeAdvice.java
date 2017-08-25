package cn.blmdz.aop.spring;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String name = (String) args[0];
		System.out.println("hi, " + name + "!");
	}

}
