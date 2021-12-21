package com.cosmos.chat.chat.authentication.domain.repository;

import com.cosmos.chat.chat.authentication.domain.model.Messaggio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessaggioRepository extends JpaRepository<Messaggio, String> {
    
}
