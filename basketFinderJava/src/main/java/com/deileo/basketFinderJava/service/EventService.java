package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.EventDto;

import java.text.ParseException;
import java.util.List;

public interface EventService {

    List<EventDto> findAll();

    Event find(Integer id);

    void save(EventDto event);

    void delete(Event event);

    List<EventDto> getCourtEvents(Court court);

    List<EventDto> getEventsByCourtType(CourtType type);
}
