package springboot.config;

import java.util.concurrent.TimeUnit;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Component
//@Configuration
//@EnableCaching
public class CachingSetup implements JCacheManagerCustomizer
{
  //@Override
  //@Bean
  public void customize(CacheManager cacheManager)
  {
    cacheManager.createCache("purchases", new MutableConfiguration<>()  
      .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 60))) 
      .setStoreByValue(false)
      .setStatisticsEnabled(true));
    
    cacheManager.createCache("userList", new MutableConfiguration<>()  
    	      .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(TimeUnit.SECONDS, 60))) 
    	      .setStoreByValue(false)
    	      .setStatisticsEnabled(true));    
  }
}
