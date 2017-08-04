package cn.blmdz.test.bean.factory;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if ("car".equals(beanName))
			System.out.println("MyInstantiationAwareBeanPostProcessor Car postProcessBeforeInstantiation");
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		if ("car".equals(beanName))
			System.out.println("MyInstantiationAwareBeanPostProcessor Car postProcessAfterInstantiation");
		return true;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
		if ("car".equals(beanName))
			System.out.println("MyInstantiationAwareBeanPostProcessor Car postProcessPropertyValues");

		return pvs;
	}
}
