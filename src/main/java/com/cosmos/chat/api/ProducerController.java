package com.cosmos.chat.api;

import javax.servlet.http.HttpServletRequest;

import com.cosmos.chat.domain.model.Messaggio;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    TopicExchange topicExchange;

    @Autowired
    SimpleMessageListenerContainer simpleMessageListenerContainer;

    //invia ad un gruppo
    @PostMapping("/send-Messaggio-Gruppo")
    public String sendToGruppo( @RequestBody Messaggio messaggio, @RequestParam String nomeGruppo, HttpServletRequest request){
        messaggio.setMittente((String)request.getSession().getAttribute("username"));
        System.out.println("routing." + nomeGruppo);
        rabbitTemplate.convertAndSend(topicExchange.getName(), "routing." + nomeGruppo, messaggio);
        return "inviato con successo";
    }

    @PostMapping("/send-Messaggio-User")
    public String sendToUser(@RequestBody Messaggio messaggio, @RequestParam String nomeUser, HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("username");
        messaggio.setMittente(username);
        rabbitTemplate.convertAndSend(topicExchange.getName(), "routing." + nomeUser, messaggio);
        return "inviato con successo";
    }

}









