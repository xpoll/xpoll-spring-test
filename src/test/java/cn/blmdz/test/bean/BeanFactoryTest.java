package cn.blmdz.test.bean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import cn.blmdz.test.reflect.Car;

public class BeanFactoryTest {

	public static void main(String[] args) {
		ResourcePatternResolver rpr = new PathMatchingResourcePatternResolver();
		
		Resource res = rpr.getResource("classpath:bean-servlet.xml");
		
		BeanFactory bf = new XmlBeanFactory(res);
		Car car = bf.getBean(Car.class);
		System.out.println(car.aaa());
		
//		FileSystemXmlApplicationContext
//		ClassPathXmlApplicationContext("classpath:bean-servlet.xml")
		ApplicationContext ac1 = new ClassPathXmlApplicationContext(new String[]{"classpath:bean-servlet.xml"});
		Car car1 = ac1.getBean(Car.class);
		System.out.println(car1.aaa());

		ApplicationContext ac2 = new AnnotationConfigApplicationContext(BeanFac.class);
		Car car2 = ac2.getBean("car", Car.class);
		System.out.println(car2.aaa());
	}
}
