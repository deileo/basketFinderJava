package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/court/{court}")
    @ResponseBody
    public List<Event> getCourtEvents(Court court) {
        return eventService.getCourtEvents(court);
    }

    @GetMapping("/{event}")
    @ResponseBody
    public Event getEvent(Event event) {
        return event;
    }

    @PostMapping()
    public ResponseEntity<String> addEvent(@Valid @RequestBody Event event) {
        eventService.save(event);

        return ResponseEntity.ok("Success");
    }

    @GetMapping("/delete/{event}")
    public ResponseEntity<String> deleteCourt(Event event) {
        eventService.delete(event);

        return ResponseEntity.ok("Success");
    }
}
