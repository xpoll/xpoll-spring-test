package cn.blmdz.jms;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class XXXQueueMessageListener implements SessionAwareMessageListener<TextMessage> {
    private static final Logger logger = LoggerFactory.getLogger(XXXQueueMessageListener.class);
    
    private static final List<MessageProcesser> list = new ArrayList<>();

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        String text = message.getText();
        logger.info(XXXQueueMessageListener.class.getName() + "-text:" + text);
        
        for (MessageProcesser messageProcesser : list) {
            messageProcesser.processMessage(message);
        }
    }
    
    public void register(MessageProcesser mp) {
        list.add(mp);
    }
}
