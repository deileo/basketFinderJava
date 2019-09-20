package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.Court;
import com.deileo.basketFinderJava.entity.Event;
import com.deileo.basketFinderJava.payload.CommentDto;
import com.deileo.basketFinderJava.service.CommentService;
import com.deileo.basketFinderJava.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ValidationUtils validation;

    @GetMapping ("/event/{event}")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> getEventComments(Event event) {
        return new ResponseEntity<>(commentService.getEventComments(event), HttpStatus.OK);
    }

    @GetMapping ("/court/{court}")
    @ResponseBody
    public ResponseEntity<List<CommentDto>> getCourtComments(Court court) {
        return new ResponseEntity<>(commentService.getCourtComments(court), HttpStatus.OK);
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseEntity<Object> newComment(@RequestBody CommentDto comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(validation.getErrorsMap(bindingResult), HttpStatus.BAD_REQUEST);
        }

        commentService.saveComment(comment);

        return new ResponseEntity<>("Success!", HttpStatus.CREATED);
    }
}
