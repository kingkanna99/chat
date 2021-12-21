package com.cosmos.chat.chat.configuration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cosmos.chat.chat.authentication.consumer.RabbitMQListner;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //creare le code in maniera dinamica
    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("exchange.topic");
    }

    @Bean
    List<String> usernames(){
        List<String> usernames = new ArrayList<>();
        usernames.add("andrea");
        usernames.add("vezza");
        usernames.add("ronca");
        /*usernames.add("g1.andrea");
        usernames.add("g1.vezza");
        usernames.add("g1.ronca");
        usernames.add("g2.andrea");
        usernames.add("g2.ronca");*/
        return usernames;
    }

    /*@Bean
    Map<String, List<String>> gruppiUtenti(){
        Map<String, List<String>> gruppiUtenti = new HashMap<>();
        String g1 = "g1";
        String g2 = "g2";
        gruppiUtenti.put("andrea", Arrays.asList(g1, g2));
        gruppiUtenti.put("vazza", Arrays.asList(g1));
        gruppiUtenti.put("ronca", Arrays.asList(g2));
        return gruppiUtenti;
    }*/

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }   

    @Bean
	ConnectionFactory connectionFactory() {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
		cachingConnectionFactory.setUsername("guest");
		cachingConnectionFactory.setUsername("guest");
		return cachingConnectionFactory;
	}

    //create MessageListenerContainer using default connection factory Queue... queues
	@Bean
	SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory ) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setMessageListener(new RabbitMQListner());
        /*Queue q = null;
        for(String s : usernames()){
            q = new Queue("queue." + s, false);
            String routing_key = "routing.g1" + s;
            Binding binding = BindingBuilder.bind(q).to(topicExchange()).with(routing_key);
            amqpAdmin().declareQueue(q);
            amqpAdmin().declareBinding(binding);
            simpleMessageListenerContainer.addQueues(q);
        }*/
		return simpleMessageListenerContainer;
	}
}











