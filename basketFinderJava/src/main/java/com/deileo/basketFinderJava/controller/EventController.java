package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.EventDto;
import com.deileo.basketFinderJava.security.CurrentUser;
import com.deileo.basketFinderJava.security.UserPrincipal;
import com.deileo.basketFinderJava.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping()
    @ResponseBody
    public List<EventDto> getEvents() {
        return eventService.findAll();
    }

    @GetMapping("/public")
    @ResponseBody
    public List<EventDto> getPublicEvents() {
        return eventService.getEventsByCourtType(CourtType.PUBLIC);
    }

    @GetMapping("/private")
    @ResponseBody
    public List<EventDto> getPrivateEvents() {
        return eventService.getEventsByCourtType(CourtType.PRIVATE);
    }

    @GetMapping("/court/{court}")
    @ResponseBody
    public List<EventDto> getCourtEvents(Court court) {
        return eventService.getCourtEvents(court);
    }

    @GetMapping("/user")
    @ResponseBody
    public List<Event> getUserEvents(@CurrentUser UserPrincipal user) {
        return new ArrayList<Event>();
    }

    @GetMapping("/user/joined/events")
    @ResponseBody
    public List<Event> getUserJoinedEvents(@CurrentUser UserPrincipal user) {
        return new ArrayList<Event>();
    }

    @PostMapping("/join/{event}")
    @ResponseBody
    public ResponseEntity<String> joinEvent(Event event) {
        eventService.joinEvent(event);

        return new ResponseEntity<>("Success!", HttpStatus.CREATED);
    }

    @PostMapping("/leave/{event}")
    @ResponseBody
    public ResponseEntity<String> leaveEvent(Event event) {
        eventService.leaveEvent(event);

        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @GetMapping("/{event}")
    @ResponseBody
    public Event getEvent(Event event) {
        return event;
    }

    @PostMapping("/new")
    public ResponseEntity<String> addEvent(@Valid @RequestBody EventDto event) throws ParseException {
        eventService.save(event);

        return new ResponseEntity<>("Success!", HttpStatus.CREATED);
    }

    @GetMapping("/delete/{event}")
    public ResponseEntity<String> deleteCourt(Event event) {
        eventService.delete(event);

        return ResponseEntity.ok("Success");
    }
}
