package cn.blmdz.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class SendListener implements ApplicationListener<ApplicationEvent> {

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof SendEvent) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			SendEvent sendEvent = (SendEvent)event;
			System.out.println(sendEvent.getTo());
		}
		
	}

}
