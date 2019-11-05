package com.deileo.basketFinderJava.repository;

import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.entity.Participant;
import com.deileo.basketFinderJava.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    @Query("SELECT p FROM Participant p WHERE p.event = :event AND p.user = :user")
    public Optional<Participant> getParticipantByEventAndUser(Event event, User user);

    @Query("SELECT p FROM Participant p INNER JOIN p.event e WHERE e.createdBy = :user AND p.isConfirmed = false")
    public List<Participant> getUnconfirmedParticipants(User user);
}