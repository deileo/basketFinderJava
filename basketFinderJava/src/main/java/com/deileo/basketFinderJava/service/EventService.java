package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.EventDto;

import java.text.ParseException;
import java.util.List;

public interface EventService {

    public List<EventDto> findAll();

    public Event find(Integer id);

    public void save(EventDto event) throws ParseException;

    public void delete(Event event);

    public List<EventDto> getCourtEvents(Court court);

    public List<EventDto> getEventsByCourtType(CourtType type);

    public void joinEvent(Event event);

    public void leaveEvent(Event event);
}
