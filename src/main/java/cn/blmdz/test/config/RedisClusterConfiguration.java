package cn.blmdz.test.config;

import java.util.Set;

import org.springframework.data.redis.connection.RedisNode;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 因spring版本过低
 * 
 * xml 中设置 property 有问题，此类继承RedisClusterConfiguration
 * 
 * @author yongzongyang
 * @date 2018年7月13日
 */
@Slf4j
public class RedisClusterConfiguration extends org.springframework.data.redis.connection.RedisClusterConfiguration {
    
    /**
     * nodes Set<RedisNode> 支持
     * 
        <property name="nodes">
            <set>
                <bean class="org.springframework.data.redis.connection.RedisNode">
                    <constructor-arg name="host" value="${redis.cluster.node.host1}"/>
                    <constructor-arg name="port" value="${redis.cluster.node.port1}"/>
                </bean>
                <bean class="org.springframework.data.redis.connection.RedisNode">
                    <constructor-arg name="host" value="${redis.cluster.node.host2}"/>
                    <constructor-arg name="port" value="${redis.cluster.node.port2}"/>
                </bean>
                <bean class="org.springframework.data.redis.connection.RedisNode">
                    <constructor-arg name="host" value="${redis.cluster.node.host3}"/>
                    <constructor-arg name="port" value="${redis.cluster.node.port4}"/>
                </bean>
            </set>
        </property>
     * @param nodes
     */
    public void setNodes(Set<RedisNode> nodes) {
        for (RedisNode redisNode : nodes) {
            log.info("-----------> host: {}, port: {}", redisNode.getHost(), redisNode.getPort());
            super.addClusterNode(redisNode);
        }
    }
    /**
     * maxRedirect 支持
     * 
     * <property name="maxRedirect" value="${redis.cluster.maxRedirect}"></property>
     * 
     * @param maxRedirect
     */
    public void setMaxRedirect(int maxRedirect) {
        super.setMaxRedirects(maxRedirect);
    }
    
    /**
     * str 支持 如：host:port,host:port,host:port
     * 
     * <property name="node" value="${redis.cluster.node}"></property>
     * 
     * @param node
     */
    public void setNode(String node) {
        try {
            for (String str : node.trim().split(",")) {
                if (!StringUtils.hasText(str)) continue;
                log.info("-----------> host: {}, port: {}", str.trim().split(":")[0].trim(), Integer.parseInt(str.trim().split(":")[1].trim()));
                super.addClusterNode(new RedisNode(str.trim().split(":")[0].trim(), Integer.parseInt(str.trim().split(":")[1].trim())));
            }
        } catch (Exception e) {
            log.error("redis cluster host:port error. value: {}", node.trim());
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
	
}
