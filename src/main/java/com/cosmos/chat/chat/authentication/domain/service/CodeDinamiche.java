package com.cosmos.chat.chat.authentication.domain.service;

import java.util.List;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeDinamiche {


    /*@Autowired
    private Map<String, List<String>> gruppiUtenti;*/
    @Autowired
    private List<String> usernames;
    @Autowired
    private TopicExchange topicExchange;
    @Autowired
    private AmqpAdmin admin;
    @Autowired
    private SimpleMessageListenerContainer simpleMessageListenerContainer;
    //data una lista di username, creare le rispettive code
    public void createUserQueue(){
        /*for (Map.Entry<String, List<String>> entry : gruppiUtenti.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            Queue q = new Queue("queue." + entry.getKey(), false);
            Binding binding = BindingBuilder.bind(q).to(headersExchange).where("destinatario").matches("g.*");
            //Binding binding = BindingBuilder.bind(q).to(headersExchange).where("destinatario").matches(entry.getValue());
            admin.declareQueue(q);
            admin.declareBinding(binding);
            simpleMessageListenerContainer.addQueues(q);
        }*/
        /*for(int i=0; i<usernames.size(); i++){
            Queue q = new Queue("queue." + usernames.get(i), false);
            String routing_key = "routing." + usernames.get(i);
            System.out.println(routing_key);
            Binding binding = BindingBuilder.bind(q).to(topicExchange).with(routing_key);
            admin.declareQueue(q);
            admin.declareBinding(binding);
            binding =  BindingBuilder.bind(q).to(topicExchange).with("routing.g1");
            admin.declareBinding(binding);          
            simpleMessageListenerContainer.addQueues(q);
        }*/
    }
    //data una lista di nomeGruppo, creare le rispettive code


    //Queue queue = new Queue(queueName, durable, false, false);
    //Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, EXCHANGE, routingKey, null);
    //admin.declareQueue(queue);
    //admin.declareBinding(binding);
}





