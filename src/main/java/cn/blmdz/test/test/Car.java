package cn.blmdz.test.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

	private String brand;
	private String color;
	private int maxSpeed;
	
	private String beanName;
	private BeanFactory beanFactory;
	
	public void setBrand(String brand) {
		System.out.println("Car setBrand");
		this.brand = brand;
	}
	public void setBeanName(String beanName) {
		System.out.println("Car setBeanName");
		this.beanName = beanName;
	}
	public void setBeanFactory(BeanFactory beanFactory) {
		System.out.println("Car setBeanFactory");
		this.beanFactory = beanFactory;
	}
	@Override
	public void afterPropertiesSet() {
		System.out.println("Car afterPropertiesSet");
	} 
	
	@Override
	public void destroy() throws Exception {
		System.out.println("Car destroy");
	}
	
	public void myinit() {
		System.out.println("Car myinit");
		aaa();
	}
	public void mydestory() {
		System.out.println("Car mydestory");
	}
	
	
	public void aaa() {
		System.out.println("Car [brand=" + brand + ", color=" + color + ", maxSpeed=" + maxSpeed + "]");
	}

	public Car() {
		System.out.println("Car NoArgsConstructor");
	}
	
	public Car(String brand, String color, int maxSpeed) {
		super();
		this.brand = brand;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
	
	
}
