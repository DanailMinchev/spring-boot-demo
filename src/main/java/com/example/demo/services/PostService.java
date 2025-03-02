package com.example.demo.services;

import com.example.demo.clients.JSONPlaceHolderClient;
import com.example.demo.dtos.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final JSONPlaceHolderClient jsonPlaceHolderClient;

    public List<PostDto> getAllPosts() {
        log.info("com.example.demo.services.PostService.getAllPosts called");
        return jsonPlaceHolderClient.getAllPosts();
    }

}
