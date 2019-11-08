package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import com.deileo.basketFinderJava.service.ParticipantService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

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

        return new ResponseEntity<>("Success!", HttpStatus.CREATED);
    }

    @PostMapping("/leave/{event}")
    public ResponseEntity<String> leaveEvent(Event event) throws NotFoundException {
        participantService.leaveEvent(event);

        return ResponseEntity.ok("Success!");
    }

    @PostMapping("/accept/{event}/{user}")
    public ResponseEntity<String> acceptParticipant(Event event, User user) throws NotFoundException {
        participantService.acceptParticipant(event, user);

        return ResponseEntity.ok("Success!");
    }

    @PostMapping("/cancel/{event}/{user}")
    public ResponseEntity<String> cancelParticipant(Event event, User user) throws NotFoundException {
        participantService.removeParticipant(event, user);

        return ResponseEntity.ok("Success!");
    }
}
