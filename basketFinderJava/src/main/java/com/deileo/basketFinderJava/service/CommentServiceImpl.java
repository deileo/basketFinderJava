package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Comment;
import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.CommentDto;
import com.deileo.basketFinderJava.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CommentDto> getEventComments(Event event) {
        List<CommentDto> comments = new ArrayList<>();
        commentRepository.getEventComments(event).forEach(comment -> comments.add(convertToDto(comment)));

        return comments;
    }

    @Override
    public List<CommentDto> getCourtComments(Court court) {
        List<CommentDto> comments = new ArrayList<>();
        commentRepository.getCourtComments(court).forEach(comment -> comments.add(convertToDto(comment)));

        return comments;
    }

    @Override
    public void saveComment(CommentDto comment) {
        commentRepository.save(convertToEntity(comment));
    }

    private CommentDto convertToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment convertToEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }
}
