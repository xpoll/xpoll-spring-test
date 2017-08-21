package cn.blmdz.aop.test;

import java.lang.reflect.Proxy;

public class ForumTest {

	public static void main(String[] args) {
		ForumService forumService = new ForumServiceImpl1();
		
		forumService.removeTopic(29);
		forumService.removeForum(10);
		
		System.out.println("-----------------------");
		
		ForumService target = new ForumServiceImpl2();
		PerformanceHandler handle = new PerformanceHandler(target);

		ForumService proxy = (ForumService) Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				handle);
		
		proxy.removeTopic(22);
		proxy.removeForum(10);
	}
}
