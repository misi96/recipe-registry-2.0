package com.sec.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.DTO.CircularEmail;
import com.sec.entity.Role;
import com.sec.entity.User;
import com.sec.repo.PictureRepository;
import com.sec.repo.PostRepository;
import com.sec.repo.RoleRepository;
import com.sec.repo.UserRepository;

@Service
//@PreAuthorize("hasRole('ADMIN')") //redundáns de biztonságosabb
public class AdminService {
	
	 @Autowired
	 UserRepository userRepository;
	 @Autowired
	 PostRepository postRepository;
	 @Autowired
	 PictureRepository pictureRepository;
	 @Autowired
	 RoleRepository roleRepository;
	 @Autowired
	 EmailService emailService;
	 
	 @Autowired
	 UserService userService;
	 
	 @PostConstruct
	 void TestUsers() {
		 
		  
		  
		 
		 roleRepository.save(new Role("ADMIN"));
		 roleRepository.save(new Role("USER"));
		 
		 adminRole = roleRepository.findByRole("ADMIN");
		 
		 
		 User user1 = new User();
		 user1.setEmail("asd");
		 user1.setUserName("asd");
		 user1.setPassword("asd");
		 
		 User user2 = new User();
		 user2.setEmail("DA");
		 user2.setUserName("banán");
		 user2.setPassword("b");
		
		 User user3 = new User();
		 user3.setEmail("blaaa");
		 user3.setUserName("citrom");
		 user3.setPassword("c");
		 
		 
		
		 
		 
		 
		
		 userService.registerUser(user1);
		 userService.registerUser(user2);
		 userService.registerUser(user3);
		 
		 
		/*userRepository.save(adminUser);
		 userRepository.save(user1);
		 userRepository.save(adminUser2);
		  */
		 
		 
	 }
	 
	 
	Role adminRole;
	 
	 
	@Transactional
	public int deleteUser(long userID) {
		
		
		return userRepository.deleteByIdAndRolesNotContaining(userID,adminRole);
		
		
	}

	@Transactional
	public int deletePost(long postID) {
		
		
		return postRepository.deleteByPostID(postID);
		
	}

	@Transactional
	public boolean LockUser(int userID) {
		userRepository.SetLockFlag(true, userID);
		return true;
		
	}

	@Transactional
	public boolean UnLockUser(int userID) {
		userRepository.SetLockFlag(false, userID);
		return true;
	}
	
	@Transactional
	public int deletePicture(long pictureID) {
		
		
		
		return pictureRepository.deleteByPictureID(pictureID);
		
	}

	
	public boolean SendCircularEmail(CircularEmail circularEmail) throws MessagingException {
		
		if(circularEmail.getTargets()==null || circularEmail.getTargets().size()==0) {
			
			List<String> targets = userRepository.getEmails();
			
			circularEmail.setTargets(targets);
			
			
			
		};
		
		emailService.sendAdminCircularEmail(circularEmail);
		
		
		return true;
	};
	
	
	
	
}
