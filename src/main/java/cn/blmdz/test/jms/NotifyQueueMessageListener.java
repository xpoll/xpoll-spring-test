package cn.blmdz.test.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * jms activity mq
 */
@Slf4j
@Component
public class NotifyQueueMessageListener implements SessionAwareMessageListener<TextMessage> {

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        log.info(message.getText());
    }
}
