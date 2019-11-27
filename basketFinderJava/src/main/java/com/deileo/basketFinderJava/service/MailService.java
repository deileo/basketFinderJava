package com.deileo.basketFinderJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String subject, String body, String[] recipients) {
        javaMailSender.send(createMessage(subject, body, recipients));
    }

    private SimpleMailMessage createMessage(String subject, String text, String[] recipients) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(recipients);
        message.setSubject(subject);
        message.setText(text);

        return message;
    }
}
