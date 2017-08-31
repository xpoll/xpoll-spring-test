package cn.blmdz.aop.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * 环绕增强
 * @date 2017年8月31日
 */
public class GreetingInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object[] objs = invocation.getArguments();
		System.out.println("hi." + objs[0]);
		Object obj = invocation.proceed();
		System.out.println("bye." + objs[0]);
		return obj;
	}
}
