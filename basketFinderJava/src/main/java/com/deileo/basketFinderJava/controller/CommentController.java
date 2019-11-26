package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.CommentDto;
import com.deileo.basketFinderJava.service.CommentService;
import com.deileo.basketFinderJava.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    private final CommentService commentService;

    private final ValidationUtils validation;

    @Autowired
    public CommentController(CommentService commentService, ValidationUtils validation) {
        this.commentService = commentService;
        this.validation = validation;
    }

    @GetMapping (value = "/event/{event}")
    public ResponseEntity<List<CommentDto>> getEventComments(Event event) {
        return ResponseEntity.ok(commentService.getEventComments(event));
    }

    @GetMapping (value = "/court/{court}")
    public ResponseEntity<List<CommentDto>> getCourtComments(Court court) {
        return ResponseEntity.ok(commentService.getCourtComments(court));
    }

    @PostMapping(value = "/new")
    public ResponseEntity<Object> newComment(@RequestBody CommentDto comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(validation.getErrorsMap(bindingResult), HttpStatus.BAD_REQUEST);
        }

        commentService.saveComment(comment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
