package com.deileo.basketFinderJava.repository;

import com.deileo.basketFinderJava.entity.Comment;
import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("SELECT c FROM Comment c WHERE c.event = :event")
    List<Comment> getEventComments(Event event);

    @Query("SELECT c FROM Comment c WHERE c.court = :court")
    List<Comment> getCourtComments(Court court);
}
