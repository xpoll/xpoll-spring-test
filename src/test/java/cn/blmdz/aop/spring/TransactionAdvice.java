package cn.blmdz.aop.spring;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

/**
 * 异常抛出增强
 * @date 2017年8月31日
 */
public class TransactionAdvice implements ThrowsAdvice {
	
	public void afterThrowing(Method method, Object[] args, Object target, Exception e) throws Throwable {
		System.out.println("--------------");
		System.out.println("method: " + method.getName());
		System.out.println("抛出异常: " + e.getMessage());
	}
}
