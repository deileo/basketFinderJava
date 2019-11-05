package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.*;
import com.deileo.basketFinderJava.payload.ParticipantDto;
import com.deileo.basketFinderJava.repository.ParticipantRepository;
import com.deileo.basketFinderJava.repository.UserRepository;
import javassist.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParticipantServiceImplTest {

    @MockBean
    private ParticipantRepository participantRepo;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private UserRepository userRepo;

    @Autowired
    private ParticipantServiceImpl participantService;

    @Test
    public void testShouldReturnEventParticipants() {
        Participant participant = new Participant();
        ParticipantDto participantDto = new ParticipantDto();
        participant.setUser(new User());
        Event event = new Event();
        event.addParticipant(participant);

        when(modelMapper.map(participant.getUser(), ParticipantDto.class)).thenReturn(participantDto);

        List<ParticipantDto> participantDtos = participantService.getEventParticipants(event);

        assertEquals(1, participantDtos.size());
        assertEquals(participantDto, participantDtos.get(0));
    }

    @Test
    public void testShouldReturnUnconfirmedEventParticipants() {
        Participant participant = new Participant();
        ParticipantDto participantDto = new ParticipantDto();
        User user = new User();
        participant.setUser(user);

        Event event = new Event();
        event.setName("EventName");
        event.setId(1);
        event.addParticipant(participant);

        mockAuthentication(user);

        when(participantRepo.getUnconfirmedParticipants(user)).thenReturn(event.getParticipants());
        when(modelMapper.map(participant.getUser(), ParticipantDto.class)).thenReturn(participantDto);

        List<ParticipantDto> participantDtos = participantService.getUnconfirmedParticipants();

        assertEquals(1, participantDtos.size());
        assertEquals(participantDto, participantDtos.get(0));
        assertEquals("EventName", participantDtos.get(0).getEventName());
    }

    @Test
    public void testShouldDeleteEventParticipant() throws NotFoundException {
        Event event = new Event();
        User user = new User();
        Participant participant = new Participant();

        mockAuthentication(user);

        when(participantRepo.getParticipantByEventAndUser(event, user)).thenReturn(Optional.of(participant));
        doNothing().when(participantRepo).delete(participant);

        participantService.leaveEvent(event);
    }

    @Test(expected = NotFoundException.class)
    public void testShouldThrowExceptionIfParticipantNotFoundOnLeave() throws NotFoundException {
        Event event = new Event();
        User user = new User();

        mockAuthentication(user);

        when(participantRepo.getParticipantByEventAndUser(event, user)).thenReturn(Optional.empty());

        participantService.leaveEvent(event);
    }

    @Test
    public void testShouldAcceptParticipant() throws NotFoundException {
        Event event = new Event();
        User user = new User();
        Participant participant = new Participant();

        when(participantRepo.getParticipantByEventAndUser(event, user)).thenReturn(Optional.of(participant));
        when(participantRepo.save(participant)).thenReturn(participant);

        participantService.acceptParticipant(event, user);
    }

    @Test(expected = NotFoundException.class)
    public void testShouldThrowExceptionIfParticipantNotFoundOnAccept() throws NotFoundException {
        Event event = new Event();
        User user = new User();

        mockAuthentication(user);

        when(participantRepo.getParticipantByEventAndUser(event, user)).thenReturn(Optional.empty());

        participantService.acceptParticipant(event, user);
    }

    private void mockAuthentication(User user) {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("email@email.com");
        when(userRepo.findByEmail("email@email.com")).thenReturn(Optional.of(user));

        SecurityContextHolder.setContext(securityContext);
    }
}
