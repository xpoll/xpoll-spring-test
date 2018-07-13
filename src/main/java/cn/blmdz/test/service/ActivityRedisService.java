package cn.blmdz.test.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActivityRedisService {


	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@PostConstruct
    public void cluster() {

        
        RedisConnection conn = null;
        try {
            conn = RedisConnectionUtils.getConnection(jedisConnectionFactory);
            conn.set("asdf".getBytes(), "sdfafvalue".getBytes());
            log.info("--------> key: {}, value: {}", "asdf", new String(conn.get("asdf".getBytes())));
            
        } finally {
            RedisConnectionUtils.releaseConnection(conn, jedisConnectionFactory);
        }
        
    }
}
