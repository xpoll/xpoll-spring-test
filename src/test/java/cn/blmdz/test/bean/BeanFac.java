package cn.blmdz.test.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.blmdz.test.test.Car;

@Configuration
public class BeanFac {

	@Bean(name="car")
	public Car car() {
		return new Car("a", "b", 2);
	}
}
