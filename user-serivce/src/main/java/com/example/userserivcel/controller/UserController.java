package com.example.userserivcel.controller;

import com.example.userserivcel.dto.UserDto;
import com.example.userserivcel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    final UserService userService;
    final Environment env;
    @Autowired
    public UserController(UserService userService, Environment env) {
        this.userService = userService;
        this.env = env;
    }

    @GetMapping("/heath_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s",
        env.getProperty("local.server.port"));
    }


    @PostMapping("/users")
    public ResponseEntity createuser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
