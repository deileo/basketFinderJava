package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ParticipantDto> getEventParticipants(Event event) {
        List<ParticipantDto> participants = new ArrayList<>();

        for (User user : event.getParticipants()) {
            participants.add(this.convertToDto(user));
        }

        return participants;
    }

    private ParticipantDto convertToDto(User user) {
        return modelMapper.map(user, ParticipantDto.class);
    }
}
