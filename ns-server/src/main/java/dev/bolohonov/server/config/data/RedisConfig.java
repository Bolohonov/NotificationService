package dev.bolohonov.server.config.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHostName;

    @Value("${spring.data.redis.port}")
    private Integer redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory(new RedisStandaloneConfiguration(redisHostName, redisPort));
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {

        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        return template;
    }
}
