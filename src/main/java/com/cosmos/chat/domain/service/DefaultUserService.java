package com.cosmos.chat.domain.service;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.cosmos.chat.domain.model.Role;
import com.cosmos.chat.domain.model.User;
import com.cosmos.chat.domain.repository.RoleRepository;
import com.cosmos.chat.domain.repository.UserRepository;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserService implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AmqpAdmin admin;

    @Autowired
    private TopicExchange topicExchange;

    @Autowired
    private SimpleMessageListenerContainer simpleMessageListenerContainer;

    /*@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    @Override
    @Transactional
    public User createUser(User user) {
        //user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("USER_ROLE");
        user.addRoles(Arrays.asList(userRole));
        //dobbiamo creare la coda permanente
        Queue q = new Queue("queue." + user.getUsername(), false);
        String routing_key = "routing." + user.getUsername();
        Binding binding = BindingBuilder.bind(q).to(topicExchange).with(routing_key);
        admin.declareQueue(q);
        admin.declareBinding(binding);
        simpleMessageListenerContainer.addQueues(q);
        return userRepository.save(user);
    }


    @Override
    public Role createRole(Role r1) {
        return roleRepository.save(r1);
    }

    @Override
    public String login(String username, String password, HttpServletRequest req) {
        Optional<User> user = userRepository.findById(username);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                //user.get().getPassword().equals(bCryptPasswordEncoder.encode(password));
                req.getSession().setAttribute("username", username);
                simpleMessageListenerContainer.addQueues(new Queue("queue." + username));
                return "credenziali corrette ";
            }
        }else{
            return "utente non trovato o password errata";
        }
        return "utente non trovato";
    }


    @Override
    public String deleteUser(String username, HttpServletRequest request) {
        String user_session = (String) request.getSession().getAttribute("username");
        if(user_session.equals(username)){
            User u = userRepository.getById(username);
            u.deleteGruppi();
            u.deleteRoles();
            userRepository.deleteById(username);
            request.getSession().invalidate();
            return "utente eliminato";
        }
        return "devi fare prima il login";
    }


    @Override
    public String invalidate(String username, HttpServletRequest request) {
        request.getSession().invalidate();
        simpleMessageListenerContainer.removeQueues(new Queue("queue." + username));
        return "sessione invalidata";
    }

    
    
}






























