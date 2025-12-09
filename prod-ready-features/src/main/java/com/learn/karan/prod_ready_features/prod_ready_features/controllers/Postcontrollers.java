package com.learn.karan.prod_ready_features.prod_ready_features.controllers;

import com.learn.karan.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.learn.karan.prod_ready_features.prod_ready_features.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class Postcontrollers {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();

    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
        return postService.createNewPost(inputPost);
    }
    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);


    }
    @PutMapping
    public PostDTO updatePost(@RequestBody PostDTO inputPost,@PathVariable Long id){
        return postService.updatePost(inputPost,id);
    }
}
