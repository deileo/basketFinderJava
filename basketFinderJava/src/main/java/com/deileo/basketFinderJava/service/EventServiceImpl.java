package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.repository.EventRepository;
import com.deileo.basketFinderJava.request.EventDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepo;

    private ModelMapper modelMapper;

    public EventServiceImpl(EventRepository eventRepo, ModelMapper modelMapper) {
        this.eventRepo = eventRepo;
        this.modelMapper = modelMapper;
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
    public void save(EventDto event) throws ParseException {
        eventRepo.save(this.convertToEntity(event));
    }

    @Override
    public void delete(Event event) {
        eventRepo.delete(event);
    }

    @Override
    public List<Event> getCourtEvents(Court court) {
        return eventRepo.getCourtEvents(court);
    }

    private Event convertToEntity(EventDto eventDto) throws ParseException {
        Event event = modelMapper.map(eventDto, Event.class);

        event.setStartTime(eventDto.convertStartTimeToDateTimeObject());

        return event;
    }
}
