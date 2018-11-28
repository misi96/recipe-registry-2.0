package com.sec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
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
	
	private static final String ADMIN_ROLE = "ADMIN";
	
	private static final String USER_ROLE = "USER";
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
    MappingService mappingService;
	
	@Autowired
	PasswordValidator passwordValidator;
	
	@PostConstruct
	void init() {
		
		roleRepository.save(new Role(USER_ROLE));
		roleRepository.save(new Role(ADMIN_ROLE));
		
		
	}
	
	@Autowired
    EmailService emailService;
	
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
	public String registerUser(User userToRegister,HttpServletRequest request) throws Exception {
		
		
		
		User userCheck = userRepository.findByEmailOrUserName(userToRegister.getEmail(), userToRegister.getUserName());
		
		if (userCheck != null)
			return "alreadyExists";
		
		
		passwordValidator.validate(userToRegister.getPassword());
		
		
		
		String base = "";
		
		
		
		if(request != null) {  //csak tesztelés alatt kell valszeg
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String ctx = request.getContextPath();
		base = url.substring(0, url.length() - uri.length() + ctx.length()) + "/";
		}
		
		Role userRole = roleRepository.findByRole(USER_ROLE);
		
		userToRegister.getRoles().add(userRole);
		
		
		
		userToRegister.setEnabled(false); //amíg nem mükszik vmi email smtp server true (egyébbként false)
		String activationKey =generateKey();
		userToRegister.setActivation(activationKey);
		
		String activationLink	= base + "activation/" + activationKey;
		
		emailService.sendMessage(userToRegister.getEmail(), activationLink);
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
		return toReturn;
    }

	@Override
	public ResponseEntity<String> userActivation(String code) {
		User user = userRepository.findByActivation(code);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/login");
		
		if (user == null)
		    return new ResponseEntity<>("Noresult",headers,HttpStatus.FOUND);
		
		user.setEnabled(true);
		user.setActivation("");
		userRepository.save(user);
		
		return new ResponseEntity<>("Activated",headers,HttpStatus.FOUND);
	}
	
	
	@Transactional
	@Override
	public List<EventDTO> GetAndDeleteEventsOfUser(User user){
		
		boolean hasEvents = userRepository.UserHasEvents(user.getId()); //nem az adatbázisbol szedi ki!a saját ConcurentHashMap-ből
		if(hasEvents) {
		
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
