package com.example.demo.clients.jsonplaceholder.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PostCommentResponseDto {

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

}