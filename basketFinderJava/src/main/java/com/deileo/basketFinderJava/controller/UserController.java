package com.deileo.basketFinderJava.controller;

import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.exception.ResourceNotFoundException;
import com.deileo.basketFinderJava.repository.UserRepository;
import com.deileo.basketFinderJava.security.CurrentUser;
import com.deileo.basketFinderJava.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}