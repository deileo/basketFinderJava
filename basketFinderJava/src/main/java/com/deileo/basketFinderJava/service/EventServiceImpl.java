package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepo;

    public EventServiceImpl(EventRepository eventRepo) {
        this.eventRepo = eventRepo;
    }

    @Override
    public List<Event> findAll() {
        return eventRepo.findAll();
    }

    @Override
    public Event find(Integer id) {
        Optional<Event> event = eventRepo.findById(id);

        return event.orElse(null);
    }

    @Override
    public void save(Event event) {
        eventRepo.save(event);
    }

    @Override
    public void delete(Event event) {
        eventRepo.delete(event);
    }

    @Override
    public List<Event> getCourtEvents(Court court) {
        return eventRepo.getCourtEvents(court);
    }
}
