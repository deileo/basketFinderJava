package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.*;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import com.deileo.basketFinderJava.repository.EventRepository;
import com.deileo.basketFinderJava.payload.EventDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper modelMapper;

    private final EventRepository eventRepo;

    @Autowired
    public EventServiceImpl(ModelMapper modelMapper, EventRepository eventRepo) {
        this.modelMapper = modelMapper;
        this.eventRepo = eventRepo;
    }

    @Override
    public List<EventDto> findAll() {
        return eventRepo.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Event find(Integer id) {
        return eventRepo.findById(id).orElse(null);
    }

    @Override
    public void save(EventDto event) {
        eventRepo.save(convertToEntity(event));
    }

    @Override
    public void delete(Event event) {
        eventRepo.delete(event);
    }

    @Override
    public List<EventDto> getCourtEvents(Court court) {
        return eventRepo.getCourtEvents(court)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EventDto> getEventsByCourtType(CourtType type) {
        return eventRepo.getEventsByCourtType(type)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Event convertToEntity(EventDto eventDto) {
        Event event = modelMapper.map(eventDto, Event.class);

        event.setStartTime(eventDto.convertStartTimeToDateTimeObject());

        if (eventDto.getEndTime() != null) {
            event.setEndTime(eventDto.convertEndTimeToDateTimeObject());
        }

        return event;
    }

    private EventDto convertToDto(Event event) {
        EventDto eventDto = modelMapper.map(event, EventDto.class);

        eventDto.setJoinedPlayers(getConfirmedParticipantsCount(event));
        eventDto.setCommentsCount(event.getComments().size());
        eventDto.setParticipants(getConfirmedParticipantsList(event));
        eventDto.setUnconfirmedParticipants(getUnconfirmedParticipantsList(event));

        return eventDto;
    }

    private Integer getConfirmedParticipantsCount(Event event) {
        return (int) event.getParticipants()
                .stream()
                .filter(participant -> participant.getConfirmed().equals(true))
                .count();
    }

    private List<ParticipantDto> getConfirmedParticipantsList(Event event) {
        return event.getParticipants()
                .stream()
                .filter(Participant::getConfirmed)
                .map(p -> modelMapper.map(p.getUser(), ParticipantDto.class))
                .collect(Collectors.toList());
    }

    private List<ParticipantDto> getUnconfirmedParticipantsList(Event event) {
        return event.getParticipants()
                .stream()
                .filter(p -> !p.getConfirmed())
                .map(p -> modelMapper.map(p.getUser(), ParticipantDto.class))
                .collect(Collectors.toList());
    }
}
