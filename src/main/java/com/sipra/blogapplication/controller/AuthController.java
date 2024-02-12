package com.sipra.blogapplication.controller;

import com.sipra.blogapplication.exceptions.ApiException;
import com.sipra.blogapplication.payLoads.JwtAuthRequest;
import com.sipra.blogapplication.security.CustomUserDetailsService;
import com.sipra.blogapplication.security.JwtAuthResponse;
import com.sipra.blogapplication.security.JwtTokenHelper;
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

@RestController
@RequestMapping("/api/users/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;


    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse>createToken(@RequestBody JwtAuthRequest request) throws Exception {

        this.authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    private void authenticate(String username,String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);
        try {
            manager.authenticate(authenticationToken);
        }catch(BadCredentialsException ex){
            System.out.println("Invalid Details");
            throw new ApiException("Invalid username and password");
        }
    }
}
