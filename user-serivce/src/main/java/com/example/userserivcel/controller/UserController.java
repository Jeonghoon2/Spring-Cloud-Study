package com.example.userserivcel.controller;

import com.example.userserivcel.dto.UserDto;
import com.example.userserivcel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service/")
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

    @GetMapping("/find_all")
    public ResponseEntity<Iterable<UserDto>> getUser(){
        Iterable<UserDto> userlist = userService.userByAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(userlist);
    }

    @PostMapping("/users")
    public ResponseEntity createuser(@RequestBody UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userDto);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable("userId") String userId){
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(userDto);
    }
}
