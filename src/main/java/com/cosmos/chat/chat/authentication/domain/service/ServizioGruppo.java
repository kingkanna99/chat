package com.cosmos.chat.chat.authentication.domain.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.cosmos.chat.chat.authentication.domain.model.Gruppo;

public interface ServizioGruppo {

    String creaGruppo(Gruppo g, HttpServletRequest req);

    Set<Gruppo> getGruppi(HttpServletRequest req);

    String deleteGruppo(String nomeGruppo, HttpServletRequest req);

    String addUserToGruppo(String username, String nomeGruppo, HttpServletRequest req);

    String deleteUserToGruppo(String username, String nomeGruppo, HttpServletRequest request);

}
