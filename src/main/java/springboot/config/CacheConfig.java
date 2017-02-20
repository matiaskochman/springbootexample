package springboot.config;

import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
	
    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache("popularPurchasesByUser", initConfiguration(Duration.FIVE_MINUTES));
            cm.createCache("userList", initConfiguration(Duration.FIVE_MINUTES));
            cm.createCache("product", initConfiguration(Duration.FIVE_MINUTES));
            cm.createCache("purchasesByProduct", initConfiguration(Duration.FIVE_MINUTES));
        };
    }
    private MutableConfiguration<Object, Object> initConfiguration(Duration duration) {
        return new MutableConfiguration<>()
                .setStoreByValue(false)
                .setStatisticsEnabled(true)
                .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(duration));
    }	
}
