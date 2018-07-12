package cn.blmdz.redis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisConnectionUtils;

import redis.clients.jedis.JedisPoolConfig;

public class ActivityRedisTest {

    static JedisPoolConfig config = new JedisPoolConfig();
    static {
        config.setMaxTotal(200);
        config.setMaxIdle(8);
        config.setMaxWaitMillis(1000 * 100);
        config.setTestOnBorrow(true);
    }
    
    public static void main(String[] args) {
//        single();
        cluster();
    }

    public static void single() {
        JedisConnectionFactory factory = new JedisConnectionFactory(config);
        factory.setHostName("xn.blmdz.cn");
        factory.setPort(6379);
        factory.setPassword("xpoll@blmdz.cn");
        factory.setTimeout(1000);
        factory.afterPropertiesSet();
        
        RedisConnection conn = null;
        
        try {
            conn = RedisConnectionUtils.getConnection(factory);
            conn.incr("a".getBytes());
            conn.incrBy("a".getBytes(), 2);
            System.out.println(new String(conn.get("a".getBytes())));
            
        } finally {
            RedisConnectionUtils.releaseConnection(conn, factory);
        }
        
    }

    public static void cluster() {

        RedisClusterConfiguration rcc = new RedisClusterConfiguration();
        Set<RedisNode> ns = new HashSet<RedisNode>();
        ns.add(new RedisNode("xn.blmdz.cn", 7001));
        ns.add(new RedisNode("xn.blmdz.cn", 7002));
        ns.add(new RedisNode("xn.blmdz.cn", 7003));
        ns.add(new RedisNode("xn.blmdz.cn", 7004));
        ns.add(new RedisNode("xn.blmdz.cn", 7005));
        ns.add(new RedisNode("xn.blmdz.cn", 7006));
        
        rcc.setClusterNodes(ns);
        JedisConnectionFactory jcf = new JedisConnectionFactory(rcc, config);
        jcf.setPassword("xpoll@blmdz.cn");
        jcf.afterPropertiesSet();
        
        RedisConnection conn = null;
        
        try {
            conn = RedisConnectionUtils.getConnection(jcf);
            conn.set("asdf".getBytes(), "sdfafvalue".getBytes());
            System.out.println(new String(conn.get("asdf".getBytes())));
            
        } finally {
            RedisConnectionUtils.releaseConnection(conn, jcf);
        }
        
    }
}
