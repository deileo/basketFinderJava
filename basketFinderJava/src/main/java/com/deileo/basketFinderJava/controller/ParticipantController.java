package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import com.deileo.basketFinderJava.service.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
}
