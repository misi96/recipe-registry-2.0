package com.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.sec.service.PasswordValidator;
import com.sec.service.PasswordValidatorZxcvbn;

@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true )
@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {
	

	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	
	@Bean
	public PasswordEncoder  encoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ActionValidator ActionValidator() {
	    return new ActionValidatorImpl();
	}
	
	
	@Bean
	@Primary
	public PasswordValidator PasswordValidator() {
	    return new PasswordValidatorZxcvbn();
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	http
			.authorizeRequests()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.antMatchers("/registration").permitAll()
				.antMatchers("/activation/**").permitAll()
				.antMatchers("/").hasAuthority("USER")
				.antMatchers("/console/**").permitAll()
				.antMatchers("/teszt").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin().and()
			.logout()
			.permitAll();
		
	
		http.csrf().disable();
        http.headers().frameOptions().disable();
	}	
	
}
