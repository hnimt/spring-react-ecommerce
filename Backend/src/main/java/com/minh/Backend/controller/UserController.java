package com.minh.Backend.controller;

import com.minh.Backend.dto.user.UserCreateDTO;
import com.minh.Backend.dto.user.UserInfoDTO;
import com.minh.Backend.entity.User;
import com.minh.Backend.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UserService userService;
    @Autowired private ModelMapper mapper;

    @GetMapping
    public ResponseEntity getAll() {
        List<User> users = userService.findAll();
        List<UserInfoDTO> results = users.stream()
                .map(user -> mapper.map(user, UserInfoDTO.class)).collect(Collectors.toList());
        return new ResponseEntity(results, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody UserCreateDTO userCreateDTO) {
        User user = mapper.map(userCreateDTO, User.class);
        UserInfoDTO result = mapper.map(userService.createUser(user), UserInfoDTO.class);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Integer id) {
        UserInfoDTO result = mapper.map(userService.findById(id), UserInfoDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateInfo(@RequestBody UserInfoDTO userDTO, @PathVariable Integer id) {
        User user = mapper.map(userDTO, User.class);
        UserInfoDTO result = mapper.map(userService.updateInfo(id, user), UserInfoDTO.class);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.remove(id);
        return new ResponseEntity(Map.of("message", "Delete successfully"), HttpStatus.OK);
    }

}
