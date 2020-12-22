package com.sar.initialize.controller;

import com.sar.initialize.service.JwtUtils;
import com.sar.initialize.service.dto.JwtAuth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/jwt/")
public class JwtController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public JwtController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> jwtLogin(@RequestBody JwtAuth jwtAuth, HttpServletResponse response) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuth.getUsername(), jwtAuth.getPassword()));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        response.addHeader("Authorization", jwtUtils.generateToken(jwtAuth.getUsername()));

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
