package com.sec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sec.DTO.EventDTO;
import com.sec.DTO.UserDTO;
import com.sec.entity.Event;
import com.sec.entity.Role;
import com.sec.entity.User;
import com.sec.repo.EventRepository;
import com.sec.repo.RoleRepository;
import com.sec.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private final String USER_ROLE = "USER";
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
    MappingService mappingService;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	return userRepository.findByEmail(username);
		
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public String registerUser(User userToRegister) {
		User userCheck = userRepository.findByEmailOrUserName(userToRegister.getEmail(), userToRegister.getUsername());

		if (userCheck != null)
			return "alreadyExists";

		Role userRole = roleRepository.findByRole(USER_ROLE);
		if (userRole != null) {
			userToRegister.getRoles().add(userRole);
		} else {
			userToRegister.addRoles(USER_ROLE);
		}
		
		userToRegister.setEnabled(true); //amíg nem mükszik vmi email smtp server (egyébbként false)
		userToRegister.setActivation(generateKey());
		
		userToRegister.setPassword(passwordEncoder.encode(userToRegister.getPassword()));
		
		userRepository.save(userToRegister);
		

		return "ok";
	}
	
	@Override
	public Page<UserDTO> ListUsers(Pageable pageable) {
		Page<User> userPage = userRepository.findAll(pageable);
		
		
		
	return mappingService.MapPages(User.class, UserDTO.class, userPage);
		
	}

	public String generateKey()
    {
		String key = "";
		Random random = new Random();
		char[] word = new char[16]; 
		for (int j = 0; j < word.length; j++) {
			word[j] = (char) ('a' + random.nextInt(26));
		}
		String toReturn = new String(word);
		log.debug("random code: " + toReturn);
		return new String(word);
    }

	@Override
	public String userActivation(String code) {
		User user = userRepository.findByActivation(code);
		if (user == null)
		    return "noresult";
		
		user.setEnabled(true);
		user.setActivation("");
		userRepository.save(user);
		return "ok";
	}
	
	
	@Transactional
	@Override
	public List<EventDTO> GetAndDeleteEventsOfUser(User user){
		
		System.out.println(user.getEventIndicator());
		if(user.getEventIndicator()	==	true) {
		List<Event> eventList = eventRepository.findByTargetUser(user);
		List<EventDTO> eventDTOList = new ArrayList<>();
		for(Event event	:eventList) {
			
			eventDTOList.add(mappingService.MapElements(event, EventDTO.class));
			
			
		}
		
		eventRepository.deleteByTargetUser(user);
		return eventDTOList;
		}
		
		return null;
	}
	
	

}
