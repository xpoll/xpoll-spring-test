package cn.blmdz.aop.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.blmdz.aop.test.ForumService;

/**
 * 引介增强
 * @date 2017年9月4日
 */
public class TestIntroduce {

	public static void main(String[] args) {

		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean-servlet.xml");
		ForumService forumService = (ForumService) ac.getBean("forumService");

		forumService.removeForum(10);
		forumService.removeTopic(222);
		System.out.println("------");
		Monitorable monitorable = (Monitorable) forumService;
		monitorable.setMonitorActive(true);
		forumService.removeForum(10);
		forumService.removeTopic(222);
//		Pointcut
//		Advice
//		Advisor
//		PointcutAdvisor
//		IntroductionAdvisor
		
	}
}
