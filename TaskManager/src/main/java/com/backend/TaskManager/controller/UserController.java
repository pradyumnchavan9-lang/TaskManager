package com.backend.TaskManager.controller;


import com.backend.TaskManager.dto.UserResponse;
import com.backend.TaskManager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/me")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getUser(){

        return new ResponseEntity<>(userService.getUser(), HttpStatus.OK);
    }
}
