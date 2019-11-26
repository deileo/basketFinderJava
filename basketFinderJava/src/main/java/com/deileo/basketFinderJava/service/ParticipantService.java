package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.payload.ParticipantDto;

import java.util.List;

public interface ParticipantService {

    List<ParticipantDto> getEventParticipants(Event event);

    List<ParticipantDto> getUnconfirmedParticipants();

    void joinEvent(Event event);

    void leaveEvent(Event event);

    void acceptParticipant(Event event, User user);

    void removeParticipant(Event event, User user);
}
