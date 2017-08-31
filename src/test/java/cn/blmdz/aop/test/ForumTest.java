package cn.blmdz.aop.test;

import java.lang.reflect.Proxy;

public class ForumTest {

	public static void main(String[] args) {
		System.out.println("-----------正常逻辑------------");
		ForumService forumService1 = new ForumServiceImpl1();
		
		forumService1.removeTopic(29);
		forumService1.removeForum(10);
		
		System.out.println("------------JDK代理-----------");
		ForumService forumService2 = (ForumService) Proxy.newProxyInstance(ForumServiceImpl2.class.getClassLoader(),
				ForumServiceImpl2.class.getInterfaces(),
				new PerformanceHandler(new ForumServiceImpl2()));
		
		forumService2.removeTopic(22);
		forumService2.removeForum(10);

		System.out.println("------------CGLib代理-----------");
		ForumService forumService3 = (ForumService) new CglibProxy().getProxy(ForumServiceImpl3.class);
		
		forumService3.removeTopic(90);
		forumService3.removeForum(12);
//		BeforeAdvice
		
	}
}
