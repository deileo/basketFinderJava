package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.exception.ResourceNotFoundException;
import com.deileo.basketFinderJava.payload.UserDto;
import com.deileo.basketFinderJava.repository.UserRepository;
import com.deileo.basketFinderJava.security.CurrentUser;
import com.deileo.basketFinderJava.security.UserPrincipal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/user/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDto> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));

        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }
}