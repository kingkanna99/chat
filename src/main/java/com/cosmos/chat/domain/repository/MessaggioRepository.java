package com.cosmos.chat.domain.repository;

import com.cosmos.chat.domain.model.Messaggio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessaggioRepository extends JpaRepository<Messaggio, String> {
    
}
