package com.deileo.basketFinderJava.security;

import com.deileo.basketFinderJava.entity.User;
import com.deileo.basketFinderJava.exception.ResourceNotFoundException;
import com.deileo.basketFinderJava.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CustomUserDetailsServiceTest {

    @MockBean
    private UserRepository userRepo;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Test
    public void testShouldLoadUserByUserName() {
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("Password");

        when(userRepo.findByEmail("email@email.com")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("email@email.com");

        assertEquals("email@email.com", userDetails.getUsername());
        assertEquals("Password", userDetails.getPassword());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testShouldThrowExceptionIfUserIsNotFound() {
        when(userRepo.findByEmail("email@email.com")).thenReturn(Optional.empty());

        userDetailsService.loadUserByUsername("email@email.com");
    }

    @Test
    public void testShouldLoadUserById() {
        User user = new User();
        user.setEmail("email@email.com");
        user.setPassword("Password");

        when(userRepo.findById(1)).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserById(1);

        assertEquals("email@email.com", userDetails.getUsername());
        assertEquals("Password", userDetails.getPassword());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testShouldThrowExceptionIfUserIsNotFoundById() {
        when(userRepo.findById(1)).thenReturn(Optional.empty());

        userDetailsService.loadUserById(1);
    }
}
