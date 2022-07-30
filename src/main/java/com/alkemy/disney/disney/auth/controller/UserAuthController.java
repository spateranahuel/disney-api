package com.alkemy.disney.disney.auth.controller;

import com.alkemy.disney.disney.auth.dto.AuthenticationRequest;
import com.alkemy.disney.disney.auth.dto.AuthenticationResponse;
import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.service.impl.JwtUtils;
import com.alkemy.disney.disney.auth.service.impl.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("auth")

public class UserAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;
    @Autowired
    private JwtUtils jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthentication(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        UserDetails usuario = userDetailsCustomService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtTokenUtil.generateToken(usuario);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody UserDTO usuarioDto) throws MessagingException {
        userDetailsCustomService.save(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
