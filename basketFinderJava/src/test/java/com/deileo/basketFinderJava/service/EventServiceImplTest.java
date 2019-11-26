package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.Participant;
import com.deileo.basketFinderJava.payload.EventDto;
import com.deileo.basketFinderJava.repository.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventServiceImplTest {

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private EventRepository eventRepo;

    @Autowired
    private EventServiceImpl eventService;

    @Test
    public void testShouldReturnAllEvents() {
        Event event = new Event();
        event.addParticipant(new Participant());
        EventDto eventDto = new EventDto();

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        when(eventRepo.findAll()).thenReturn(eventList);
        when(modelMapper.map(event, EventDto.class)).thenReturn(eventDto);

        List<EventDto> eventDtos = eventService.findAll();

        assertEquals(1, eventDtos.size());
        assertEquals(eventDto, eventDtos.get(0));
    }

    @Test
    public void testShouldReturnEventById() {
        Event eventObject = new Event();
        Optional<Event> event = Optional.of(eventObject);

        when(eventRepo.findById(1)).thenReturn(event);

        assertEquals(eventObject, eventService.find(1));
    }

    @Test
    public void testShouldSaveEvent() throws ParseException {
        Event event = new Event();
        EventDto eventDto = new EventDto();
        eventDto.setStartTime("2019-01-01T18:00:00");

        when(modelMapper.map(eventDto, Event.class)).thenReturn(event);
        when(eventRepo.save(event)).thenReturn(event);

        eventService.save(eventDto);
    }

    @Test
    public void testShouldDeleteEvent() {
        Event event = new Event();

        doNothing().when(eventRepo).delete(event);

        eventService.delete(event);
    }

    @Test
    public void testShouldReturnEventByCourtType() {
        Event event = new Event();
        EventDto eventDto = new EventDto();
        event.addParticipant(new Participant());

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        when(eventRepo.getEventsByCourtType(CourtType.PUBLIC)).thenReturn(eventList);
        when(modelMapper.map(event, EventDto.class)).thenReturn(eventDto);

        List<EventDto> eventDtos = eventService.getEventsByCourtType(CourtType.PUBLIC);

        assertEquals(1, eventDtos.size());
        assertEquals(eventDto, eventDtos.get(0));
    }

    @Test
    public void testShouldReturnEventForCourt() {
        Court court = new Court();
        Event event = new Event();
        EventDto eventDto = new EventDto();
        event.addParticipant(new Participant());

        List<Event> eventList = new ArrayList<>();
        eventList.add(event);

        when(eventRepo.getCourtEvents(court)).thenReturn(eventList);
        when(modelMapper.map(event, EventDto.class)).thenReturn(eventDto);

        List<EventDto> eventDtos = eventService.getCourtEvents(court);

        assertEquals(1, eventDtos.size());
        assertEquals(eventDto, eventDtos.get(0));
    }
}
