package com.deileo.basketFinderJava.repository;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.CourtType;
import com.deileo.basketFinderJava.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM Event e WHERE e.court = :court")
    List<Event> getCourtEvents(Court court);

    @Query("SELECT e FROM Event e INNER JOIN e.court c WHERE c.type = :type")
    List<Event> getEventsByCourtType(@Param("type") CourtType type);
}
