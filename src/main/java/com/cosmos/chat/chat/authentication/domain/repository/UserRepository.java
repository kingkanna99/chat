package com.cosmos.chat.chat.authentication.domain.repository;

import com.cosmos.chat.chat.authentication.domain.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

//public interface UserRepository extends JpaRepository<User, Integer>{
public interface UserRepository extends JpaRepository<User, String>{


}
