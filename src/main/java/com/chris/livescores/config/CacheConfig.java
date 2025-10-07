package com.chris.livescores.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager mgr = new CaffeineCacheManager("teams", "gamesToday", "gameById");
        // uses Caffeine's spec syntax; no Caffeine import needed
        mgr.setCacheSpecification("maximumSize=500,expireAfterWrite=10s");
        return mgr;
    }
}
