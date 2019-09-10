package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;

import java.util.List;

public interface EventService {
    public List<Event> findAll();

    public Event find(Integer id);

    public void save(Event event);

    public void delete(Event event);

    public List<Event> getCourtEvents(Court court);
}
