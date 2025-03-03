package com.example.demo.web.controllers;

import com.example.demo.models.PostModel;
import com.example.demo.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/load")
    public ResponseEntity<Void> loadPosts() {
        log.info("PostController.loadPosts called");
        postService.loadPosts();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    private ResponseEntity<List<PostModel>> findAll(Pageable pageable) {
        log.info("PostController.findAll(pageable) called with pageable: {}", pageable);
        List<PostModel> postModels = postService.findAll(pageable);
        return ResponseEntity.ok(postModels);
    }

}
