package com.cosmos.chat.domain.repository;

import com.cosmos.chat.domain.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String>{
    Role findByName(String name);
    
}
