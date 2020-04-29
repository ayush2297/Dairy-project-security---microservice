package com.thoughtworks.dairy.security.service;

import com.thoughtworks.dairy.security.exception.AuthenticationServerException;
import com.thoughtworks.dairy.security.model.User;
import com.thoughtworks.dairy.security.repository.UserRepository;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @org.junit.Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @org.junit.Test
    public void shouldLoadUserByUsername() {
        Long id = 1L;
        String userName = "userName";
        String pass = "pass";
        String email = "email@gmail.com";
        User user = new User(id, userName, pass, email);
        when(userRepository.findByUserName(userName)).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
        assertEquals(userName,userDetails.getUsername());
        assertEquals(pass,userDetails.getPassword());
        verify(userRepository,times(1)).findByUserName(userName);
    }

    @org.junit.Test
    public void shouldThrowExceptionIfUserByUsernameDoesNotExist() {
        String userName = "userName";
        expectedException.expect(AuthenticationServerException.class);
        expectedException.expectMessage("user not found!");
        when(userRepository.findByUserName(userName)).thenThrow(new AuthenticationServerException("user not found!", BAD_REQUEST));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
    }



}