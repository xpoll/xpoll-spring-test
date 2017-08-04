package cn.blmdz.test.bean.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import cn.blmdz.test.test.Car;

public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if ("car".equals(beanName)) {
			Car car = (Car) bean;
			if (car.getColor() == null) {
				System.out.println("MyBeanPostProcessor Car postProcessBeforeInitialization");
				car.setColor("黑色");
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if ("car".equals(beanName)) {
			Car car = (Car) bean;
			if (car.getMaxSpeed() >= 200) {
				System.out.println("MyBeanPostProcessor Car postProcessAfterInitialization");
				car.setMaxSpeed(200);
			}
		}
		return bean;
	}

}
