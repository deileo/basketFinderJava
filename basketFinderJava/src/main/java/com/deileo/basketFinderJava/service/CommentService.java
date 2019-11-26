package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> getEventComments(Event event);

    List<CommentDto> getCourtComments(Court court);

    void saveComment(CommentDto comment);
}
