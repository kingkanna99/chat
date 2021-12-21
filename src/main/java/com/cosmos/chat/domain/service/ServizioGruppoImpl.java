package com.cosmos.chat.domain.service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import com.cosmos.chat.domain.model.Gruppo;
import com.cosmos.chat.domain.model.User;
import com.cosmos.chat.domain.repository.GruppoRepository;
import com.cosmos.chat.domain.repository.UserRepository;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServizioGruppoImpl implements ServizioGruppo{

    @Autowired
    private AmqpAdmin admin;

    @Autowired
    private TopicExchange topicExchange;
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    GruppoRepository gruppoRepository;

    @Override
    @Transactional
    public String creaGruppo(Gruppo g, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");
        if(username==null){
            return "devi fare il login";
        }
        gruppoRepository.save(g);
        User u = userRepository.getById(username);
        u.addGroups(Arrays.asList(g));
        userRepository.save(u);
        //aggiungere binding key gruppo
        Queue q = new Queue("queue." + username, false);
        //String routing_key = "routing." + g.getNomeGruppo() + "." + username;
        String routing_key = "routing." + g.getNomeGruppo();
        Binding binding = BindingBuilder.bind(q).to(topicExchange).with(routing_key);
        admin.declareBinding(binding);
        return "gruppo aggiunto";
    }

    @Override
    public Set<Gruppo> getGruppi(HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");
        if(username == null)
            return null;
        Optional<User> u = userRepository.findById(username);
        return u.get().getGroups();
    }

    @Override
    @Transactional
    public String deleteGruppo(String nomeGruppo, HttpServletRequest req) {
        String username = (String) req.getSession().getAttribute("username");
        if(username == null)
            return "login non effettuato";
        Optional<User> u = userRepository.findById(username);
        Optional<Gruppo> g = gruppoRepository.findById(nomeGruppo);
        if(!u.get().getGroups().contains(g.get())){
            return "non appartieni al gruppo";
        }
        System.out.println(g.get().getNomeGruppo());
        gruppoRepository.delete(g.get());
        return "gruppo eliminato";
    }

    @Override
    public String addUserToGruppo(String username, String nomeGruppo, HttpServletRequest req) {
        String current_username = (String) req.getSession().getAttribute("username");
        if(current_username == null)
            return "login non effettuato";
        User current_user = userRepository.getById(current_username);
        Optional<User> user = userRepository.findById(username);
        if(!user.isPresent()){
            return "utente da aggiungere non trovato";
        }
        Optional<Gruppo> g = gruppoRepository.findById(nomeGruppo);
        if(!current_user.getGroups().contains(g.get())){
            return "utente non appartenente al gruppo";
        }
        user.get().addGroups(Arrays.asList(g.get()));
        //aggiungere binding key gruppo
        Queue q = new Queue("queue." + username, false);
        //String routing_key = "routing." + nomeGruppo + "." + username;
        String routing_key = "routing." + nomeGruppo;
        Binding binding = BindingBuilder.bind(q).to(topicExchange).with(routing_key);
        admin.declareBinding(binding);
        return "utente aggiunto al gruppo";
    }
    @Override
    public String deleteUserToGruppo(String username, String nomeGruppo, HttpServletRequest request){
        String current_username = (String) request.getSession().getAttribute("username");
        if(current_username == null)
            return "login non effetuato";
        Optional<User> current_user = userRepository.findById(current_username);
        Optional<User> user = userRepository.findById(username);
        if(!user.isPresent()){
            return "utente da aggiungere non trovato";
        }
        Optional<Gruppo> g = gruppoRepository.findById(nomeGruppo);
        if(!current_user.get().getGroups().contains(g.get())){
            return "l'utente non appartiene al gruppo";
        }
        user.get().deleteGruppo(nomeGruppo);
        //togliere binding key gruppo
        Queue q = new Queue("queue." + username, false);
        String routing_key = "routing." + nomeGruppo + "." + username;
        Binding binding = BindingBuilder.bind(q).to(topicExchange).with(routing_key);
        admin.removeBinding(binding);
        return "utente rimosso dal gruppo";
    }

    
    
}














