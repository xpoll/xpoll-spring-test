package cn.blmdz.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 容器事件
 * 
 * 同步
 */
public class SenderTest {

	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:bean-servlet.xml");
		Sender sender = ac.getBean(Sender.class);
		sender.send("ffffffff");
	}
}
