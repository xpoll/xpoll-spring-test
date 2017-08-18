package cn.blmdz.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

public class SendEvent extends ApplicationContextEvent {
	
	private static final long serialVersionUID = 1L;
	
	private String to;

	public SendEvent(ApplicationContext source, String to) {
		super(source);
		this.to = to;
	}

	public String getTo() {
		return this.to;
	}
}
