package cn.blmdz.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Sender implements ApplicationContextAware {
	
	private ApplicationContext ac;

	@Override
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		this.ac = ac;
	}

	public void send(String to) {
		System.out.println("send before -----");
		ac.publishEvent(new SendEvent(this.ac, to));
		System.out.println("send after -----");
	}
}
