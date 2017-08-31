package cn.blmdz.aop.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApplication {

	public static void main(String[] args) throws Exception {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean-servlet.xml");
		Waiter waiter = (Waiter) ac.getBean("waiter");
		waiter.greetTo("小小小");
		waiter.serveTo("小小小");
		waiter.doit("小小小");
		waiter.create("小小小");
	}
}
