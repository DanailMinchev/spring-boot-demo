package com.example.demo.web.controllers;

import com.example.demo.models.PostModel;
import com.example.demo.services.PostService;
import com.example.demo.web.controllers.dtos.UpdatePostModelRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<PostModel>> findAll(Pageable pageable) {
        log.info("PostController.findAll(pageable) called with pageable: {}", pageable);

        List<PostModel> postModels = postService.findAll(pageable);

        return ResponseEntity.ok(postModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostModel> findById(@PathVariable Long id) {
        log.info("PostController.findById(id) called with id: {}", id);

        Optional<PostModel> postModelOptional = postService.findById(id);

        return postModelOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PostModel> updateById(@PathVariable Long id,
                                                @RequestBody UpdatePostModelRequest request) {
        log.info("PostController.updateById(id, request) called with id: {}, request: {}", id, request);

        Optional<PostModel> postModelOptional = postService.updateById(id, request);

        return postModelOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        log.info("PostController.deleteById(id) called with id: {}", id);

        if (postService.existsById(id)) {
            postService.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
