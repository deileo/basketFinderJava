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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepo;

    private final ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepo, ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentDto> getEventComments(Event event) {
        return commentRepo.getEventComments(event)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getCourtComments(Court court) {
        return commentRepo.getCourtComments(court)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveComment(CommentDto comment) {
        commentRepo.save(convertToEntity(comment));
    }

    private CommentDto convertToDto(Comment comment) {
        return modelMapper.map(comment, CommentDto.class);
    }

    private Comment convertToEntity(CommentDto commentDto) {
        return modelMapper.map(commentDto, Comment.class);
    }
}
