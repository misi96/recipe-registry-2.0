package com.sec.service;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.DTO.CircularEmail;
import com.sec.entity.Role;
import com.sec.entity.User;
import com.sec.repo.PictureRepository;
import com.sec.repo.PostRepository;
import com.sec.repo.RecipeRepository;
import com.sec.repo.RoleRepository;
import com.sec.repo.UserRepository;

@Service
@PreAuthorize("hasAuthority('ADMIN')") //redundáns de biztonságosabb
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
	 RecipeRepository recipeRepository;
	 
	 @Autowired
	 UserService userService;
	 
	 @Value("${recipes.pictures.fileNames.Prefix}")
	 String fileNamePrefix;
		
	 @Value("${recipes.pictures.fileNames.Suffix}")
	 String fileNameSuffix;
	 
	 final String upLoadDir = "src" + File.separator + "main" + File.separator +  "resources" + File.separator + "static" + File.separator + "MainPicturesOfRecipes" + File.separator;
	 
	 Role adminRole;
	 @PostConstruct
	 void TestUsers() {
		 
		  
		  
		
		 adminRole = roleRepository.findByRole("ADMIN");
		 
		 
		 User user1 = new User();
		 user1.setEmail("asd");
		 user1.setUserName("asd");
		 user1.setPassword("asd123");
		 user1.getRoles().add(adminRole);
		 
		 
		 
		 User user2 = new User();
		 user2.setEmail("DA");
		 user2.setUserName("banán");
		 user2.setPassword("blad342324");
		
		 User user3 = new User();
		 user3.setEmail("blaaa");
		 user3.setUserName("citrom");
		 user3.setPassword("cifgsdfn23vv");
		 
		 
		
		 
		 
		 
		try {
		 userService.registerUser(user1,null);
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			
			 userService.registerUser(user2,null);
			
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		
		try {
		
			 userService.registerUser(user3,null);
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		
		
		 
	 }
	 
	 
	
	 
	 
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
	public int deleteAttachedPicture(long pictureID) {
		
		
		
		return pictureRepository.deleteByPictureID(pictureID);
		
	}
	
	public int deleteMainPicture(long postableID) {
		
		
		String fileName = fileNamePrefix + postableID + fileNameSuffix;
		File outputfile = new File(upLoadDir + fileName);
		if(outputfile.exists() && outputfile.delete()) {
			
			recipeRepository.SetMainPicFlag(false, postableID);
			return 1;
			
		}else {
			
			return 0;
		
		}
			
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
