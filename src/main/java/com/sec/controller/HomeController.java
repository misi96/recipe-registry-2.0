package com.sec.controller;


import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sec.entity.User;
import com.sec.service.EmailService;
import com.sec.service.UserService;


@Controller
public class HomeController {
	
//    private final Logger log = LoggerFactory.getLogger(this.getClass());
//    
//    private UserService userService;
//    
//    private EmailService emailService;
//
//	@Autowired
//	public void setEmailService(EmailService emailService) {
//		this.emailService = emailService;
//	}
//	
//	@Autowired
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
//	
//	
//	
//	
//	
//	
//	
////	@RequestMapping("/registration")
////	public String registration(Model model){
////		model.addAttribute("user", new User());
////		return "registration";
////	}
//	
////	@RequestMapping(value = "/reg", method = RequestMethod.POST)
//	@PostMapping("/reg")
//    public String reg(@ModelAttribute User user) {
//		log.info("Uj user!");
////		emailService.sendMessage(user.getEmail());
//		log.debug(user.getUsername());
//		log.debug(user.getEmail());
//		log.debug(user.getPassword());
//		userService.registerUser(user);
//        return "auth/login";
//    }
//	
//	 @RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
//	    public String activation(@PathVariable("code") String code, HttpServletResponse response) {
//		String result = userService.userActivation(code);
//		return "auth/login";
//	 }

}
