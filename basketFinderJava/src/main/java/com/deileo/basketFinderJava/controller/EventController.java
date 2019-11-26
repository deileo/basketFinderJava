package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.EventDto;
import com.deileo.basketFinderJava.security.CurrentUser;
import com.deileo.basketFinderJava.security.UserPrincipal;
import com.deileo.basketFinderJava.service.EventService;
import com.deileo.basketFinderJava.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/events", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    private final EventService eventService;

    private final ValidationUtils validation;

    @Autowired
    public EventController(EventService eventService, ValidationUtils validation) {
        this.eventService = eventService;
        this.validation = validation;
    }

    @GetMapping()
    public ResponseEntity<List<EventDto>> getEvents() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/public")
    public ResponseEntity<List<EventDto>> getPublicEvents() {
        return ResponseEntity.ok(eventService.getEventsByCourtType(CourtType.PUBLIC));
    }

    @GetMapping("/private")
    public ResponseEntity<List<EventDto>> getPrivateEvents() {
        return ResponseEntity.ok(eventService.getEventsByCourtType(CourtType.PRIVATE));
    }

    @GetMapping("/court/{court}")
    public ResponseEntity<List<EventDto>> getCourtEvents(Court court) {
        return ResponseEntity.ok(eventService.getCourtEvents(court));
    }

    @GetMapping("/user")
    public List<Event> getUserEvents(@CurrentUser UserPrincipal user) {
        return new ArrayList<Event>();
    }

    @GetMapping("/user/joined/events")
    public List<Event> getUserJoinedEvents(@CurrentUser UserPrincipal user) {
        return new ArrayList<Event>();
    }

    @GetMapping("/{event}")
    public ResponseEntity<Event> getEvent(Event event) {
        return ResponseEntity.ok(event);
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addEvent(@Valid @RequestBody EventDto event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(validation.getErrorsMap(bindingResult), HttpStatus.BAD_REQUEST);
        }

        eventService.save(event);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{event}")
    public ResponseEntity<String> deleteCourt(Event event) {
        eventService.delete(event);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
