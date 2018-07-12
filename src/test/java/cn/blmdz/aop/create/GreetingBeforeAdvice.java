package cn.blmdz.aop.create;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

/**
 * 前置增强
 * @date 2017年8月31日
 */
public class GreetingBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println(target.getClass().getName() + "#" + method.getName());
		String name = (String) args[0];
		System.out.println("hi, " + name + "!");
	}

}
