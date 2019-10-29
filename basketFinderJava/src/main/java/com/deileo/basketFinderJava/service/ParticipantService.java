package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import javassist.NotFoundException;

import java.util.List;

public interface ParticipantService {

    public List<ParticipantDto> getEventParticipants(Event event);

    public List<ParticipantDto> getUnconfirmedParticipants();

    public void joinEvent(Event event);

    public void leaveEvent(Event event) throws NotFoundException;

    public void acceptParticipant(Event event, User user) throws NotFoundException;

    public void removeParticipant(Event event, User user) throws NotFoundException;
}
