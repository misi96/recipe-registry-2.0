package com.sec.service;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sec.DTO.CircularEmail;

@Service
public class EmailService {
    private final Log log = LogFactory.getLog(this.getClass());
    
	@Value("${spring.mail.username}")
	private String MESSAGE_FROM;
	
	
	
	@Autowired
	private JavaMailSender javaMailSender;


	public void sendMessage(String email) {
		SimpleMailMessage message = null;
		
		try {
			message = new SimpleMailMessage();
			message.setFrom(MESSAGE_FROM);
			message.setTo(email);
			message.setSubject("Sikeres regisztrálás");
			message.setText("Kedves " + email + "! \n \n Köszönjük, hogy regisztráltál az oldalunkra!");
			javaMailSender.send(message);
			
		} catch (Exception e) {
			log.error("Hiba e-mail küldéskor az alábbi címre: " + email + "  " + e);
		}
	} 

	
	
	//@PreAuthorize("hasRole('ADMIN')")
	public void sendAdminCircularEmail(CircularEmail circularEmail) throws MessagingException {
		
		
		
		List<String> targets = circularEmail.getTargets();
		SimpleMailMessage[] simpleMailMessage = new SimpleMailMessage[targets.size()];
		String subject = circularEmail.getSubject();
		String body = circularEmail.getBody();
		int i =0;
		for(String target: targets ) {
		
			SimpleMailMessage message = new SimpleMailMessage();
			simpleMailMessage[i] =  message;
			simpleMailMessage[i].setFrom(MESSAGE_FROM);
			simpleMailMessage[i].setTo(target);
			simpleMailMessage[i].setSubject(subject);
			simpleMailMessage[i].setText(body);
			i++;
			
		}
		

		try {	
			
		javaMailSender.send(simpleMailMessage);	
			
		}catch (Exception e) {
			log.error("Hiba a körlevél küldésekor : " + e.getMessage());
		}
		
		
		
		
	}
	
	
	
	
}
