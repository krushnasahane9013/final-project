package com.despicable.controllers;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.despicable.dto.AuthenticationRequest;
import com.despicable.dto.AuthenticationResponse;
import com.despicable.entities.User;
import com.despicable.repository.UserRepository;
import com.despicable.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserRepository userRepository;
    
    public static final String TOKEN_PREFIX="Bearer";
    public static final String HEADE_STRING="Authorization";
 

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    
    public void createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect Username or Password");
        }catch (DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not activated");
			// return  ;
		}

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        
        if(optionalUser.isPresent()) {
        	response.getWriter().write(new JSONObject()
        			.put("userId",optionalUser.get().getId())
        			.put("role",optionalUser.get().getRole())
        			.put("token",jwt)
        			.toString());
        }
       // response.setHeader("Access-control-Expose-Headers","Authorization");
      //  response.setHeader("Access-Control-Allow-Headers", "Authorization,X-Pingother,Origin,X-Requested-With,Content-Type,Accept,X-Custom-header");
        response.setHeader(HEADE_STRING,TOKEN_PREFIX+jwt);
        //return jwt;
    }
}
