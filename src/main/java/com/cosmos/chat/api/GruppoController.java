package com.cosmos.chat.api;

import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.cosmos.chat.domain.model.Gruppo;
import com.cosmos.chat.domain.service.ServizioGruppo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class GruppoController {
    
    @Autowired
	private ServizioGruppo servizioGruppo;

    @PostMapping(value="/add-Gruppo")
    public String postMethodName(@RequestBody Gruppo g, HttpServletRequest req) {
        return servizioGruppo.creaGruppo(g, req);
    } 
    @GetMapping(value="/get-Gruppi")
    public Set<Gruppo> getGruppi(HttpServletRequest req){
        return servizioGruppo.getGruppi(req);
    }

    @PostMapping(value="/delete-Gruppo")
    public String deleteGruppo(@RequestParam String nomeGruppo, HttpServletRequest req){

        return servizioGruppo.deleteGruppo(nomeGruppo, req);
    }
    @PostMapping(value = "/add-UserToGruppo")
    public String addUserToGruppo(@RequestParam String username, @RequestParam String nomeGruppo ,HttpServletRequest req){
        return servizioGruppo.addUserToGruppo(username, nomeGruppo, req);
    }

    @PostMapping(value = "/delete-UserToGruppo")
    public String deleteUserToGruppo(@RequestParam String username, @RequestParam String nomeGruppo ,HttpServletRequest req){
        return servizioGruppo.deleteUserToGruppo(username, nomeGruppo, req);
    }
}
