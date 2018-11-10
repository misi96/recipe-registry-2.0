package com.sec.repo;
import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByRole(String role);
	
}