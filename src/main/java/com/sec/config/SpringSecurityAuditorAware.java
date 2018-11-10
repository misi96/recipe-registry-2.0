package com.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sec.entity.User;
import com.sec.repo.UserRepository;



class SpringSecurityAuditorAware implements AuditorAware<User> {

	@Autowired
	UserRepository userRepo;
  public User getCurrentAuditor() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }
    
    return userRepo.findByUserName((String) authentication.getPrincipal());
  }
}