package br.com.lovizoto.externalapi.services;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SessionCacheService {

    /**
     * Attempts to retrieve a sessionId from the "sessions" cache.
     * The cache key will be the user’s internal ID (e.g., "user-uuid-123").
     * If not found, it will return null.
     */
    @Cacheable(value = "sessions", key = "#userId")
    public String getSessionId(String userId) {
        // This method will only be executed if the session is NOT in the cache.
        // Since we only want to retrieve, we return null to force the creation of a new session.
        return null;
    }

    /**
     * Puts (or updates) a sessionId in the "sessions" cache.
     * The @CachePut annotation ensures that the method will ALWAYS be executed and that
     * its return value (the sessionId) will be placed in the cache.
     * The 2-hour TTL configured in RedisConfig will apply here.
     */
    @CachePut(value = "sessions", key = "#userId")
    public String cacheSessionId(String userId, String sessionId) {
        return sessionId;
    }
}
