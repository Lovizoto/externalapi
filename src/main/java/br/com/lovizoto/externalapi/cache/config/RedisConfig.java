package br.com.lovizoto.externalapi.cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class RedisConfig {
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

        // TTL for "users" cache
        cacheConfigurations.put("users", RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(30)));

        // TTL for "sessions" cache
        cacheConfigurations.put("sessions", RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(2)));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofHours(1)) // default TTL
                                .disableCachingNullValues()
                )
                .withInitialCacheConfigurations(cacheConfigurations) // personal customs
                .build();
    }
}
