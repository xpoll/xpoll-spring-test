package cn.blmdz.test.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.blmdz.test.test.Car;

public class ReflectTest {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		System.out.println(loader);
		System.out.println(loader.getParent());
		System.out.println(loader.getParent().getParent());
		Class clazz = loader.loadClass("cn.blmdz.test.reflect.Car");

		Constructor<Car> cons = clazz.getDeclaredConstructor(null);
		
		Car car = cons.newInstance();
		Method m1 = clazz.getMethod("setBrand", String.class);
		m1.invoke(car, "sb");
		car.aaa();
//		Resource
//		ResourceLoader
//		BeanFactory
//		BeanDefinition
	}
}
