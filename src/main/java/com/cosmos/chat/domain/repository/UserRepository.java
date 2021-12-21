package com.cosmos.chat.domain.repository;

import com.cosmos.chat.domain.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String>{


}
