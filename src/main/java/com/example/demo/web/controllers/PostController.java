package com.example.demo.web.controllers;

import com.example.demo.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/load")
    public ResponseEntity<Void> loadPosts() {
        postService.loadPosts();
        return ResponseEntity.ok().build();
    }

}
