package com.blog.app.controller;


import com.blog.app.model.Role;
import com.blog.app.model.User;
import com.blog.app.model.UserDto;
import com.blog.app.service.RoleService;
import com.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/profile")
public class ProfileController {


    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;


    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam("id") Long id){
        User user;
        user = userService.getUserById(id);

        if (Objects.isNull(user)){
            return new ResponseEntity<>("user not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Long id){
        String result = userService.deleteUserById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }



    @GetMapping("/roles")
    public ResponseEntity<?> getUserRole(@RequestParam("username") String username){
        User user = userService.getUserByUsername(username);
        if (Objects.isNull(user)){
            return new ResponseEntity<>("user not found", HttpStatus.OK);
        }
        List<Role> roleList = user.getRoles();
        return new ResponseEntity<>(roleList, HttpStatus.OK);
    }


    @GetMapping("/test")
    public String getTest(){
        return "User test success";
    }
}
