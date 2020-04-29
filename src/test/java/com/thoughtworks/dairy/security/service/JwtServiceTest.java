package com.thoughtworks.dairy.security.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JwtServiceTest {

    @InjectMocks
    private JwtService jwtService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGenerateToken() {
        String username = "ayush";
        assertEquals(String.class, jwtService.generateToken(username).getClass());
    }

    @Test
    public void shouldExtractUsernameFromToken() {
        String username = "ayush";
        String token = jwtService.generateToken(username);
        String extractedUsername = jwtService.extractUsername(token);
        assertEquals(username,extractedUsername);
    }

    @Test
    public void shouldValidateToken() {
        String username = "ayush";
        UserDetails userDetails = new User(username,"password",new ArrayList<>());
        String token = jwtService.generateToken(username);
        assertEquals(true,jwtService.validateToken(token,userDetails));
    }

}