package com.deileo.basketFinderJava.consumer;

import com.deileo.basketFinderJava.entity.Participant;
import com.deileo.basketFinderJava.service.MailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class EventConsumer {

    private MailService mailService;

    @Autowired
    public EventConsumer(MailService mailService) {
        this.mailService = mailService;
    }

    @RabbitListener(queues = "participantServiceQueue")
    public void receive(Participant participant) {
        final String[] recipients = participant.getEvent().getParticipants()
                .stream()
                .filter(p -> !p.equals(participant))
                .map(p -> p.getUser().getEmail())
                .toArray(String[]::new);

        final String text = "New participant "
                + participant.getUser().getName()
                + " joined for event: "
                + participant.getEvent().getName();

        mailService.send("New participant joined!", text, recipients);
    }
}
