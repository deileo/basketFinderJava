package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.Participant;
import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.exception.NotFoundException;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import com.deileo.basketFinderJava.repository.ParticipantRepository;
import com.deileo.basketFinderJava.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository participantRepo;

    private final ModelMapper modelMapper;

    private final UserRepository userRepo;

    @Autowired
    public ParticipantServiceImpl(
            ParticipantRepository participantRepo,
            ModelMapper modelMapper,
            UserRepository userRepo
    ) {
        this.participantRepo = participantRepo;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
    }

    @Override
    public List<ParticipantDto> getEventParticipants(Event event) {
        return event.getParticipants()
                .stream()
                .map(p -> convertToDto(p.getUser()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ParticipantDto> getUnconfirmedParticipants() {
        return participantRepo.getUnconfirmedParticipants(getCurrentUser())
                .stream()
                .map(p -> convertToDto(p.getUser(), p.getEvent().getName(), p.getEvent().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void joinEvent(Event event) {
        Boolean isConfirmed = event.getCourt().getType().equals(CourtType.PUBLIC) || event.getCreatedBy().equals(getCurrentUser());

        participantRepo.save(new Participant(event, getCurrentUser(), isConfirmed));
    }

    @Override
    public void leaveEvent(Event event) {
        Participant participant = participantRepo.getParticipantByEventAndUser(event, getCurrentUser()).orElseThrow(
            () -> new NotFoundException("Participant not found")
        );

        participantRepo.delete(participant);
    }

    @Override
    public void acceptParticipant(Event event, User user) {
        Participant participant = participantRepo.getParticipantByEventAndUser(event, user).orElseThrow(
            () -> new NotFoundException("Participant not found")
        );

        participant.setConfirmed(Boolean.TRUE);

        participantRepo.save(participant);
    }

    @Override
    public void removeParticipant(Event event, User user) {
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

        return userRepo.findByEmail(auth.getName()).orElse(null);
    }
}
