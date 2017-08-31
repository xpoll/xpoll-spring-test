package cn.blmdz.aop.spring;

import org.springframework.aop.framework.ProxyFactory;

public class TestCode {

	public static void main(String[] args) throws Exception {
		Waiter waiter = new NaiveWaiter();
		
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setInterfaces(waiter.getClass().getInterfaces());
//		proxyFactory.setOptimize(true);//true 强制使用cglib代理
		proxyFactory.setTarget(waiter);
		proxyFactory.addAdvice(new GreetingBeforeAdvice());
		proxyFactory.addAdvice(new GreetingAfterAdvice());
		proxyFactory.addAdvice(new GreetingInterceptor());
		proxyFactory.addAdvice(new TransactionAdvice());
		
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("Tom");
		proxy.serveTo("Tom");
		proxy.doit("Tom");
		proxy.create("Tom");
//		ProxyFactoryBean
//		AopProxy
//		IntroductionInterceptor
	}
}
