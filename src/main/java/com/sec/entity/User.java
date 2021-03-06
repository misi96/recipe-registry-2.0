package com.sec.entity;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table( name="users" )
public class User implements UserDetails{

	


	@Id 
	@GeneratedValue
	private long id;
	
	@Column( unique=true, nullable=false )
	private String email;
	
	@Column( nullable=false )
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	
	@Column( nullable=false ,length=30,unique =true)
	private String userName;
	
	@JsonProperty(access = Access.READ_ONLY)
	private Boolean locked = false;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String activation;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name="eventIndicator")
	private boolean eventIndicator = false;
	
	@JsonProperty(access = Access.READ_ONLY)
	private boolean enabled = false;
	
	@JsonProperty(access = Access.READ_ONLY)
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="targetUser",fetch=FetchType.LAZY)
	List<Event> events;
	
	
	@ManyToMany( cascade = CascadeType.MERGE, fetch = FetchType.EAGER )
	@JoinTable( 
		name = "users_roles", 
		joinColumns = {@JoinColumn(name="user_id")}, 
		inverseJoinColumns = {@JoinColumn(name="role_id")}  
	)
	@JsonProperty(access = Access.READ_ONLY)
	private Set<Role> roles = new HashSet<Role>();
	
	
	public boolean getEventIndicator() {
		return eventIndicator;
	}

	public void setEventIndicator(boolean eventIndicator) {
		this.eventIndicator = eventIndicator;
	}

	public User() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String UserName) {
		this.userName = UserName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public void addRoles(String roleName) {
		if (this.roles == null || this.roles.isEmpty()) 
			this.roles = new HashSet<>();
		this.roles.add(new Role(roleName));
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + " userName =" + userName + " ]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		Set<Role> roles = getRoles();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getRole()));
			System.out.println(role.getRole() + " rolesdebug ");
		}
		return authorities;
	}

	@Override
	public String getUsername() {
		
		return this.email; 
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return !locked;
	}
	

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {
	
		return enabled;
	}

	public void setEvents(List<Event> event) {
		this.events =event;
		
	}
	

	
}