package com.deileo.basketFinderJava.config;

import com.deileo.basketFinderJava.consumer.EventConsumer;
import com.deileo.basketFinderJava.service.MailService;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventProducerConfiguration {

    private final MailService mailService;

    @Autowired
    public EventProducerConfiguration(MailService mailService) {
        this.mailService = mailService;
    }

    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("eventExchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("participantServiceQueue");
    }

    @Bean
    public Binding binding(Queue queue, Exchange eventExchange) {
        return BindingBuilder
                .bind(queue)
                .to(eventExchange)
                .with("participant.*")
                .noargs();
    }

    @Bean
    public EventConsumer eventReceiver() {
        return new EventConsumer(mailService);
    }
}
