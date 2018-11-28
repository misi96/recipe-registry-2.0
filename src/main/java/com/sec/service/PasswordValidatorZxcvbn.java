package com.sec.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;


@Component
public class PasswordValidatorZxcvbn  extends Zxcvbn implements	PasswordValidator {
	
	@Value("${security.Zxcvbn.password.minSecStrength:3}")
	int minSecStrength;
	
	@Override
	public void validate(String password) throws Exception {
		
		Strength strength = measure(password);
		if (strength.getScore() < minSecStrength) {
			throw new Exception("Too week password ");
			}
		
	}

}
