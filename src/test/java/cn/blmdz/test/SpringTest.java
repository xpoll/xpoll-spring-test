package cn.blmdz.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	
	private static HiAction hiAction;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AbstractApplicationContext cxt = new ClassPathXmlApplicationContext("bean-servlet.xml");
		hiAction = (HiAction) cxt.getBean("hiAction");
	}

	@Test
	public void save() {
		System.out.println(hiAction.getHi());
	}

}
