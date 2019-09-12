package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.repository.EventRepository;
import com.deileo.basketFinderJava.payload.EventDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EventDto> findAll() {
        List<EventDto> events = new ArrayList<>();
        for (Event event : eventRepo.findAll()) {
            events.add(this.convertToDto(event));
        }

        return events;
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
    public List<EventDto> getCourtEvents(Court court) {
        List<EventDto> events = new ArrayList<>();
        for (Event event : eventRepo.getCourtEvents(court)) {
            events.add(this.convertToDto(event));
        }

        return events;
    }

    @Override
    public List<EventDto> getEventsByCourtType(CourtType type) {
        List<EventDto> events = new ArrayList<>();
        for (Event event : eventRepo.getEventsByCourtType(type)) {
            events.add(this.convertToDto(event));
        }

        return events;
    }

    private Event convertToEntity(EventDto eventDto) throws ParseException {
        Event event = modelMapper.map(eventDto, Event.class);

        event.setStartTime(eventDto.convertStartTimeToDateTimeObject());

        return event;
    }

    private EventDto convertToDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }
}
