package com.blog.app.service;


import com.blog.app.model.Post;
import com.blog.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post savePost(Post post){
        return postRepository.save(post);
    }

    public Post getPost(Long id){
        return postRepository.findById(id).orElse(null);
    }

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public Post updatePost(Post post){
        return postRepository.save(post);
    }

    public String deletePost(Long id){
        Post post = getPost(id);
        if (Objects.nonNull(post)){
            postRepository.delete(post);
            return "post deleted";
        }
        return null;
    }
}
