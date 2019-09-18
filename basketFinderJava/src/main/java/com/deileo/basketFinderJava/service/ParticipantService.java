package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.ParticipantDto;

import java.util.List;

public interface ParticipantService {

    public List<ParticipantDto> getEventParticipants(Event event);
}
