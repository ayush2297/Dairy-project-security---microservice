package com.thoughtworks.dairy.security.controller;

import com.thoughtworks.dairy.security.dto.AuthRequest;
import com.thoughtworks.dairy.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "/hello", method = PUT)
    public String message() {
        return "HELLO EVERYONE";
    }

    @RequestMapping(value = "/authenticate", method = POST)
    public String authenticateUser(@Valid @RequestBody AuthRequest authRequest) {
        return tokenService.getToken(authRequest);
    }
}
