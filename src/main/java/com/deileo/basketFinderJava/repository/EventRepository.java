package com.deileo.basketFinderJava.repository;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e FROM Event e WHERE e.court = :court")
    public List<Event> getCourtEvents(Court court);
}
