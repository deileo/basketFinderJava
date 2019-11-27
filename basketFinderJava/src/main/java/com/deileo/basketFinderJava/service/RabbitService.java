package com.deileo.basketFinderJava.service;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitService {

    private final RabbitTemplate template;

    private final Exchange exchange;

    @Autowired
    public RabbitService(RabbitTemplate template, Exchange exchange) {
        this.template = template;
        this.exchange = exchange;
    }

    void convertAndSend(String routingName, Object object) {
        template.convertAndSend(exchange.getName(), routingName, object);
    }
}
