package com.cosmos.chat.domain.repository;

import com.cosmos.chat.domain.model.Gruppo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GruppoRepository extends JpaRepository<Gruppo, String> {
    Gruppo findByNomeGruppo(String nomeGruppo);
}
