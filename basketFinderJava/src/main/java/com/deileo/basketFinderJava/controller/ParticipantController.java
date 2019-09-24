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
    @ResponseBody
    public List<ParticipantDto> getEventParticipants(Event event) {
        return participantService.getEventParticipants(event);
    }

    @GetMapping("/unconfirmed")
    @ResponseBody
    public List<ParticipantDto> getUnconfirmedParticipants() {
        return participantService.getUnconfirmedParticipants();
    }

    @PostMapping("/join/{event}")
    @ResponseBody
    public ResponseEntity<String> joinEvent(Event event) {
        if (event.getParticipants().size() >= event.getNeededPlayers()) {
            return new ResponseEntity<>("Event is full with players!", HttpStatus.BAD_REQUEST);
        }

        participantService.joinEvent(event);

        return new ResponseEntity<>("Success!", HttpStatus.CREATED);
    }

    @PostMapping("/leave/{event}")
    @ResponseBody
    public ResponseEntity<String> leaveEvent(Event event) {
        participantService.leaveEvent(event);

        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @PostMapping("/accept/{event}/{user}")
    @ResponseBody
    public ResponseEntity<String> acceptParticipant(Event event, User user) throws NotFoundException {
        participantService.acceptParticipant(event, user);

        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }

    @PostMapping("/cancel/{event}/{user}")
    @ResponseBody
    public ResponseEntity<String> cancelParticipant(Event event, User user) {
        participantService.removeParticipant(event, user);

        return new ResponseEntity<>("Success!", HttpStatus.OK);
    }
}
