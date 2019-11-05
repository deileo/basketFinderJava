package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.Participant;
import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import com.deileo.basketFinderJava.repository.ParticipantRepository;
import com.deileo.basketFinderJava.repository.UserRepository;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

    @Autowired
    private ParticipantRepository participantRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ParticipantDto> getEventParticipants(Event event) {
        List<ParticipantDto> participants = new ArrayList<>();
        event.getParticipants().forEach(participant -> participants.add(convertToDto(participant.getUser())));

        return participants;
    }

    @Override
    public List<ParticipantDto> getUnconfirmedParticipants() {
        List<ParticipantDto> participants = new ArrayList<>();

        participantRepo.getUnconfirmedParticipants(getCurrentUser()).forEach(participant -> {
            Event event = participant.getEvent();
            participants.add(convertToDto(participant.getUser(), event.getName(), event.getId()));
        });

        return participants;
    }

    @Override
    public void joinEvent(Event event) {
        Boolean isConfirmed = event.getCourt().getType().equals(CourtType.PUBLIC) || event.getCreatedBy().equals(getCurrentUser());

        participantRepo.save(new Participant(event, getCurrentUser(), isConfirmed));
    }

    @Override
    public void leaveEvent(Event event) throws NotFoundException {
        Participant participant = participantRepo.getParticipantByEventAndUser(event, getCurrentUser()).orElseThrow(
            () -> new NotFoundException("Participant not found")
        );

        participantRepo.delete(participant);
    }

    @Override
    public void acceptParticipant(Event event, User user) throws NotFoundException {
        Participant participant = participantRepo.getParticipantByEventAndUser(event, user).orElseThrow(
            () -> new NotFoundException("Participant not found")
        );

        participant.setConfirmed(Boolean.TRUE);

        participantRepo.save(participant);
    }

    @Override
    public void removeParticipant(Event event, User user) throws NotFoundException {
        Participant participant = participantRepo.getParticipantByEventAndUser(event, user).orElseThrow(
            () -> new NotFoundException("Participant not found")
        );

        participantRepo.delete(participant);
    }

    private ParticipantDto convertToDto(User user) {
        return modelMapper.map(user, ParticipantDto.class);
    }

    private ParticipantDto convertToDto(User user, String eventName, Integer eventId) {
        ParticipantDto participantDto = modelMapper.map(user, ParticipantDto.class);
        participantDto.setEventName(eventName);
        participantDto.setEventId(eventId);

        return participantDto;
    }

    private User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByEmail(auth.getName()).orElse(null);
    }
}
