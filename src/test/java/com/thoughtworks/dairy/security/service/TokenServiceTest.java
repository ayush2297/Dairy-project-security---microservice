package com.thoughtworks.dairy.security.service;

import com.thoughtworks.dairy.security.dto.AuthRequest;
import com.thoughtworks.dairy.security.exception.AuthenticationServerException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TokenServiceTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private TokenService tokenService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetToken() {
        String user = "user";
        String pass = "pass";
        String token = "token";
        AuthRequest authRequest = new AuthRequest(user, pass);
        Authentication authenticatedUser = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authenticatedUser);
        when(authenticatedUser.getName()).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);
        String actualToken = tokenService.getToken(authRequest);
        assertEquals(token,actualToken);
    }

    @Test
    public void shouldThrowExceptionIfIncorrectCredentialsAreUsed() {
        expectedException.expect(AuthenticationServerException.class);
        expectedException.expectMessage("invalid username/password");
        String user = "user";
        String pass = "pass";
        String token = "token";
        AuthRequest authRequest = new AuthRequest(user, pass);
        when(jwtService.generateToken(user)).thenReturn(token);
        String actualToken = tokenService.getToken(authRequest);
        assertEquals(token,actualToken);
    }
}