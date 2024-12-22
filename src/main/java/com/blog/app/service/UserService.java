package com.blog.app.service;


import com.blog.app.model.User;
import com.blog.app.model.UserDto;
import com.blog.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;



    public User saveUser(User user){
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User getUserByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }

    public String deleteUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if (Objects.nonNull(user)){
            userRepository.delete(user);
            return "success";
        }
        return "user not found";
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

}
