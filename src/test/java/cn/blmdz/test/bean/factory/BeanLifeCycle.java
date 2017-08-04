package cn.blmdz.test.bean.factory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import cn.blmdz.test.test.Car;

/**
 * <pre>
 * BeanFactory 中 Bean 的生命周期
 * BeanPostProcessor
 * InstantiationAwareBeanPostProcessorAdapter
 * 
 * ···
 * InstantiationAwareBeanPostProcessorAdapter.postProcessBeforeInstantiation
 * Object.NoArgsConstructor
 * InstantiationAwareBeanPostProcessorAdapter.postProcessAfterInstantiation
 * InstantiationAwareBeanPostProcessorAdapter.postProcessPropertyValues
 * Object.setMethod
 * BeanPostProcessor.postProcessBeforeInitialization
 * Object.afterPropertiesSet
 * Object.myInit
 * BeanPostProcessor.postProcessAfterInitialization
 * Object.destroy
 * Object.myDestroy
 * </pre>
 */
@SuppressWarnings("deprecation")
public class BeanLifeCycle {

	public static void main(String[] args) {
		Resource resource = new ClassPathResource("bean-servlet.xml");
		BeanFactory beanFactory = new XmlBeanFactory(resource);

		((ConfigurableBeanFactory)beanFactory).addBeanPostProcessor(new MyBeanPostProcessor());
		((ConfigurableBeanFactory)beanFactory).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
		
		Car car1 = (Car) beanFactory.getBean("car");
		System.out.println(car1);
		car1.aaa();
		car1.setColor("红色");

		Car car2 = beanFactory.getBean(Car.class);
		System.out.println(car1 == car2);
		
		((XmlBeanFactory)beanFactory).destroySingletons();
		car2.aaa();
	}
}
