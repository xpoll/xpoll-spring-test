package cn.blmdz.test.reflect;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

	private String brand;
	private String color;
	private int maxSpeed;
	
	
	
	public String aaa() {
		return "Car [brand=" + brand + ", color=" + color + ", maxSpeed=" + maxSpeed + "]";
	}

	public Car() {
	}
	
	public Car(String brand, String color, int maxSpeed) {
		super();
		this.brand = brand;
		this.color = color;
		this.maxSpeed = maxSpeed;
	}
}
