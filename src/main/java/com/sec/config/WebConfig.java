package com.sec.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
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

	@Bean
	public Module datatypeHibernateModule() {
	  return new Hibernate4Module();
	}
	
	
	
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
	
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
	    MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
	    methodInvokingFactoryBean.setTargetClass(SecurityContextHolder.class);
	    methodInvokingFactoryBean.setTargetMethod("setStrategyName");
	    methodInvokingFactoryBean.setArguments(new String[]{SecurityContextHolder.MODE_INHERITABLETHREADLOCAL});
	    return methodInvokingFactoryBean;
	}

	
	
}
