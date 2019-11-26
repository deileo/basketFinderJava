package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import com.deileo.basketFinderJava.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/participants", produces = MediaType.APPLICATION_JSON_VALUE)
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("/{event}")
    public ResponseEntity<List<ParticipantDto>> getEventParticipants(Event event) {
        return ResponseEntity.ok(participantService.getEventParticipants(event));
    }

    @GetMapping("/unconfirmed")
    public ResponseEntity<List<ParticipantDto>> getUnconfirmedParticipants() {
        return ResponseEntity.ok(participantService.getUnconfirmedParticipants());
    }

    @PostMapping("/join/{event}")
    public ResponseEntity<String> joinEvent(Event event) {
        if (event.getParticipants().size() >= event.getNeededPlayers()) {
            return new ResponseEntity<>("Event is full with players!", HttpStatus.BAD_REQUEST);
        }

        participantService.joinEvent(event);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/leave/{event}")
    public ResponseEntity<String> leaveEvent(Event event) {
        participantService.leaveEvent(event);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/accept/{event}/{user}")
    public ResponseEntity<String> acceptParticipant(Event event, User user) {
        participantService.acceptParticipant(event, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cancel/{event}/{user}")
    public ResponseEntity<String> cancelParticipant(Event event, User user) {
        participantService.removeParticipant(event, user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
