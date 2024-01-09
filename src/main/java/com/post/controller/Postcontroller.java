package com.post.controller;

import com.post.entity.Post;
import com.post.payload.PostDto;
import com.post.seccurity.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post-service")
@RequiredArgsConstructor //To generate the required injection
public class Postcontroller {


    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody Post post, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
            PostDto postDto = postService.createPost(post);

        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
}
