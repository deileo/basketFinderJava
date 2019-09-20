package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.CommentDto;

import java.util.List;

public interface CommentService {

    public List<CommentDto> getEventComments(Event event);

    public List<CommentDto> getCourtComments(Court court);

    public void saveComment(CommentDto comment);
}
