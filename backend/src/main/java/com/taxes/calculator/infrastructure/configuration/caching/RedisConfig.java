package com.taxes.calculator.infrastructure.configuration.caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RedisConfiguration {

    @Bean
    CacheManager cacheManager() {
	return new ConcurrentMapCacheManager(
		"calculateHourValue", "orderprice",
		"HourValueGetById", "VariableTaxGetById",
		"fixedTaxGetById");
    }

}