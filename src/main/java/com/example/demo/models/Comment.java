package com.example.demo.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Comment {

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

}
