package com.cosmos.chat.chat.authentication.domain.repository;

import com.cosmos.chat.chat.authentication.domain.model.Gruppo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GruppoRepository extends JpaRepository<Gruppo, String> {
    Gruppo findByNomeGruppo(String nomeGruppo);
}
