package com.sec.config;

import java.util.concurrent.Executor;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sec.entity.CommentListener;
import com.sec.entity.User;
import com.sec.service.MappingService;

@Configuration
@EnableJpaAuditing
@EnableCaching
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		super.addViewControllers(registry);
        registry.addViewController("/login").setViewName("auth/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);		
	}
	
	@Bean
	  public AuditorAware<User> auditorProvider() {
		
	    return new SpringSecurityAuditorAware();
	  }
	
	@Bean
	  public MappingService mappingService() {
		
	    return new MappingService();
	  }
	
	@Bean
	public AutowireHelper autowireHelper(){
	    return AutowireHelper.getInstance();
	}
	
	/*@Bean
    public CacheManager cacheManagerUser() {
        return new ConcurrentMapCacheManager("Users");
    }
	@Bean
    public CacheManager cacheManagerPost() {
        return new ConcurrentMapCacheManager("Posts");
    }
	*/
	
	@Bean
    public Executor taskExecutor() {
		
		
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("Thread-");
        executor.initialize();
        return executor;
        
        
    }

	
	
}
