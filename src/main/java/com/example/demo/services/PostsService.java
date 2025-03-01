package com.example.demo.services;

import com.example.demo.clients.JSONPlaceHolderClient;
import com.example.demo.models.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostsService {

    private final JSONPlaceHolderClient jsonPlaceHolderClient;

    public List<Post> getAllPosts() {
        log.info("com.example.demo.services.PostsService.getAllPosts called");
        return jsonPlaceHolderClient.getAllPosts();
    }

}
