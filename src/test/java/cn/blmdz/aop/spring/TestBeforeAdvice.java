package cn.blmdz.aop.spring;

import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class TestBeforeAdvice {

	public static void main(String[] args) {
		Waiter waiter = new NaiveWaiter();
		BeforeAdvice beforeAdvice = new GreetingBeforeAdvice();
		
		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(waiter);
		proxyFactory.addAdvice(beforeAdvice);
		
		Waiter proxy = (Waiter) proxyFactory.getProxy();
		proxy.greetTo("Tom");
		proxy.serveTo("Tom");
	}
}
