package com.cosmos.chat.chat.authentication.domain.repository;

import com.cosmos.chat.chat.authentication.domain.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String>{
    Role findByName(String name);
    
}
