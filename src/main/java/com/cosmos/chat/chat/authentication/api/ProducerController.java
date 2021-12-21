package com.cosmos.chat.chat.authentication.api;

import javax.servlet.http.HttpServletRequest;

import com.cosmos.chat.chat.authentication.domain.model.Messaggio;

import org.springframework.amqp.core.Queue;
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
    /*@Autowired
    private MessageConverter messageConverter;
    /*@Autowired
    private DirectExchange directExchange;*/
    //@Autowired
    //FanoutExchange fanoutExchange;
    @Autowired
    TopicExchange topicExchange;

    @Autowired
    SimpleMessageListenerContainer simpleMessageListenerContainer;
    /*@Autowired
    HeadersExchange headersExchange;*/

    /*@PostMapping("/send-Messaggio-Direct")
    public String sendDirect( @RequestBody Messaggio messaggio){
        rabbitTemplate.convertAndSend(directExchange.getName(), "routing.A", messaggio);
        return "inviato con successo";
    }*/

    /*@PostMapping("/send-Messaggio-Fanout")
    public String sendFanout( @RequestBody Messaggio messaggio){
        rabbitTemplate.convertAndSend(fanoutExchange.getName(), "", messaggio);
        return "inviato con successo";
    }*/
    //invia ad un gruppo
    @PostMapping("/send-Messaggio-Gruppo")
    public String sendToGruppo( @RequestBody Messaggio messaggio, @RequestParam String nomeGruppo, HttpServletRequest request){
        messaggio.setMittente((String)request.getSession().getAttribute("username"));
        System.out.println("routing." + nomeGruppo);
        //non è giusto he il consumatore sia deciso dal produttore, devono essere due cose staccate
        //a tutte le code degli user che appartengono a quel determinato gruppo
        //simpleMessageListenerContainer.addQueues(new Queue("queue"));
        rabbitTemplate.convertAndSend(topicExchange.getName(), "routing." + nomeGruppo, messaggio);
        return "inviato con successo";
    }

    @PostMapping("/send-Messaggio-User")
    public String sendToUser(@RequestBody Messaggio messaggio, @RequestParam String nomeUser, HttpServletRequest request){
        String username = (String)request.getSession().getAttribute("username");
        messaggio.setMittente(username);
        //simpleMessageListenerContainer.addQueues(new Queue("queue." + nomeUser));
        rabbitTemplate.convertAndSend(topicExchange.getName(), "routing." + nomeUser, messaggio);
        return "inviato con successo";
    }



    /*@PostMapping("/send-Messaggio-Header")
    public String sendHeader( @RequestBody Messaggio messaggio){
        MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("destinatario", messaggio.getDestinatario());
		MessageConverter messageConverter = new SimpleMessageConverter();
		Message message = messageConverter.toMessage(messaggio.getDestinatario(), messageProperties);
		rabbitTemplate.send(headersExchange.getName(), "", message);
        return "inviato con successo";
    }*/



    //inviare un messaggio ad un utente nel db
    //inviare un messaggio ad un utente del gruppo
}









