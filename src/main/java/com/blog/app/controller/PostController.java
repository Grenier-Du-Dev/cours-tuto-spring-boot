package com.blog.app.controller;


import com.blog.app.model.Post;
import com.blog.app.model.PostDto;
import com.blog.app.model.User;
import com.blog.app.service.PostService;
import com.blog.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> addPost(@RequestBody PostDto postDto){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userService.getUserByUsername(userDetails.getUsername());
            Post post = new Post();
            post.setPostTitle(postDto.getPostTitle());
            post.setPostContent(postDto.getPostContent());
            user.addPost(post);
            Post savedPost = postService.savePost(post);

            return new ResponseEntity<>("Post saved", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getPostList(){
        List<Post> postList = postService.getAllPost();
        return new ResponseEntity<>(postList, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPost(@RequestParam Long id){
        Post post = postService.getPost(id);
        if (Objects.nonNull(post)){
            return new ResponseEntity<>(post, HttpStatus.OK);
        }
        return new ResponseEntity<>("Post not found", HttpStatus.OK);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deletePost(@RequestParam Long id){
        String  result = postService.deletePost(id);
        if (Objects.nonNull(result)){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("Post not found", HttpStatus.OK);
    }
}
