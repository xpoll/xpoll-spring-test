package cn.blmdz.jms;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SurveryProcesser implements MessageProcesser {
	private final  static BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
	private final ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

	@Autowired
    private XXXQueueMessageListener queueMessage;

    @Override
    @PostConstruct
    public void register(MessageProcesser mp) throws InterruptedException {
        queueMessage.register(this);
        while(true){
            final Message message = messageQueue.take();
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        message.getJMSMessageID();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    
	@Override
	public void processMessage(Message message) {
		try {
			messageQueue.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
