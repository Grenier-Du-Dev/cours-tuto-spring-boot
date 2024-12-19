package com.blog.app.controller;


import com.blog.app.model.User;
import com.blog.app.model.UserDto;
import com.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save", produces = "Application/json")
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userService.saveUser(user);

        if (Objects.nonNull(savedUser)){
            UserDto userDto1 = new UserDto();
            userDto1.setUsername(savedUser.getUsername());
            userDto1.setPassword(savedUser.getPassword());
            return new ResponseEntity<>(userDto1, HttpStatus.OK);
        }

        return new ResponseEntity<>("cannot save the user. something went wrong", HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam("id") Long id){
        User user;
        user = userService.getUserById(id);

        if (Objects.isNull(user)){
            return new ResponseEntity<>("user not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser(){
        List<User> userList = userService.getAllUser();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Long id){
        String result = userService.deleteUserById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String getTest(){
        return "User test success";
    }
}
