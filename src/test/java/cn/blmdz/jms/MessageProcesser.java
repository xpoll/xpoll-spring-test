package cn.blmdz.jms;

import javax.jms.Message;

public interface MessageProcesser {
	void  processMessage(Message message);
	
	void register(MessageProcesser mp) throws InterruptedException;
}
