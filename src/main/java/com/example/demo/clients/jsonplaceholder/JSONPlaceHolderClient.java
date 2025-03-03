package com.example.demo.clients.jsonplaceholder;

import com.example.demo.clients.jsonplaceholder.dtos.PostCommentResponseDto;
import com.example.demo.clients.jsonplaceholder.dtos.PostResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "jsonplaceholder")
public interface JSONPlaceHolderClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts", produces = "application/json")
    List<PostResponseDto> getAllPosts();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{postId}/comments", produces = "application/json")
    List<PostCommentResponseDto> getAllPostCommentsByPostId(@PathVariable("postId") Long postId);

}
