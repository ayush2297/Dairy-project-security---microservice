package com.thoughtworks.dairy.security.service;

import com.thoughtworks.dairy.security.dto.AuthRequest;
import com.thoughtworks.dairy.security.exception.AuthenticationServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class TokenService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String getToken(AuthRequest authRequest) throws AuthenticationServerException {
        try {
            Authentication authenticatedUser = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            return jwtService.generateToken(authenticatedUser.getName());
        } catch (Exception e) {
            throw new AuthenticationServerException("invalid username/password", BAD_REQUEST);
        }
    }
}
