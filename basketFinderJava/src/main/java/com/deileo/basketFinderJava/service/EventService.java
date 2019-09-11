package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.request.EventDto;

import java.text.ParseException;
import java.util.List;

public interface EventService {
    public List<Event> findAll();

    public Event find(Integer id);

    public void save(EventDto event) throws ParseException;

    public void delete(Event event);

    public List<Event> getCourtEvents(Court court);
}
