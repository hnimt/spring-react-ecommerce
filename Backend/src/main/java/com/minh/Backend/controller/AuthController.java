package com.minh.Backend.controller;

import com.minh.Backend.dto.jwt.JwtRequest;
import com.minh.Backend.dto.jwt.JwtResponse;
import com.minh.Backend.dto.user.UserCreateDTO;
import com.minh.Backend.dto.user.UserInfoDTO;
import com.minh.Backend.entity.User;
import com.minh.Backend.service.impl.UserService;
import com.minh.Backend.utility.JWTUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired private ModelMapper mapper;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody UserCreateDTO userCreateDTO) {
        User user = userService.createUser(userCreateDTO);
        UserInfoDTO result = mapper.map(user, UserInfoDTO.class);
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        String token = jwtUtility.generateToken(userDetails);
        result.setToken(token);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody JwtRequest jwtRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
                jwtUtility.generateToken(userDetails);
        User user = userService.findByUsername(jwtUtility.getUsernameFromToken(token));
        UserInfoDTO result = mapper.map(user, UserInfoDTO.class);
        result.setToken(token);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
