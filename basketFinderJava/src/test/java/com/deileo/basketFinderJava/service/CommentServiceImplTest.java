package com.deileo.basketFinderJava.service;

import com.deileo.basketFinderJava.entity.Comment;
import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.CommentDto;
import com.deileo.basketFinderJava.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private CommentRepository userRepo;

    @Test
    public void testShouldReturnEventComments() {
        Event event = new Event();
        Comment comment = createComment("New comment!");
        CommentDto commentDto = createCommentDto("New comment!");

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        when(userRepo.getEventComments(event)).thenReturn(comments);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        List<CommentDto> commentsList = commentService.getEventComments(event);

        assertEquals(1, commentsList.size());
        assertEquals(commentsList.get(0), commentDto);
    }

    @Test
    public void testShouldReturnCourtComments() {
        Court court = new Court();
        Comment comment = createComment("New comment!");
        CommentDto commentDto = createCommentDto("New comment!");

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        when(userRepo.getCourtComments(court)).thenReturn(comments);
        when(modelMapper.map(comment, CommentDto.class)).thenReturn(commentDto);

        List<CommentDto> commentsList = commentService.getCourtComments(court);

        assertEquals(1, commentsList.size());
        assertEquals(commentsList.get(0), commentDto);
    }

    @Test
    public void testShouldSaveNewComment() {
        CommentDto commentDto = createCommentDto("New comment!");
        Comment comment = createComment("New comment!");

        when(modelMapper.map(commentDto, Comment.class)).thenReturn(comment);
        when(userRepo.save(comment)).thenReturn(comment);

        commentService.saveComment(commentDto);
    }

    private Comment createComment(String comment) {
        Comment commentObject = new Comment();
        commentObject.setComment(comment);

        return commentObject;
    }

    private CommentDto createCommentDto(String comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment);

        return commentDto;
    }
}
