package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.*;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import com.deileo.basketFinderJava.repository.EventRepository;
import com.deileo.basketFinderJava.payload.EventDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventRepository eventRepo;

    @Override
    public List<EventDto> findAll() {
        List<EventDto> events = new ArrayList<>();
        for (Event event : eventRepo.findAll()) {
            events.add(convertToDto(event));
        }

        return events;
    }

    @Override
    public Event find(Integer id) {
        return eventRepo.findById(id).orElse(null);
    }

    @Override
    public void save(EventDto event) throws ParseException {
        eventRepo.save(convertToEntity(event));
    }

    @Override
    public void delete(Event event) {
        eventRepo.delete(event);
    }

    @Override
    public List<EventDto> getCourtEvents(Court court) {
        List<EventDto> events = new ArrayList<>();
        for (Event event : eventRepo.getCourtEvents(court)) {
            events.add(convertToDto(event));
        }

        return events;
    }

    @Override
    public List<EventDto> getEventsByCourtType(CourtType type) {
        List<EventDto> events = new ArrayList<>();
        for (Event event : eventRepo.getEventsByCourtType(type)) {
            events.add(convertToDto(event));
        }

        return events;
    }

    private Event convertToEntity(EventDto eventDto) throws ParseException {
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
        List<ParticipantDto> participantDtos = new ArrayList<>();
        event.getParticipants().forEach(participant -> {
            if (participant.getConfirmed()) {
                participantDtos.add(modelMapper.map(participant.getUser(), ParticipantDto.class));
            }
        });

        return participantDtos;
    }

    private List<ParticipantDto> getUnconfirmedParticipantsList(Event event) {
        List<ParticipantDto> participantDtos = new ArrayList<>();
        event.getParticipants().forEach(participant -> {
            if (!participant.getConfirmed()) {
                participantDtos.add(modelMapper.map(participant.getUser(), ParticipantDto.class));
            }
        });

        return participantDtos;
    }
}
