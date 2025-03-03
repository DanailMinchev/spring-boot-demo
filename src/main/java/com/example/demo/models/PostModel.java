package com.example.demo.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class PostModel {

    private Long id;
    private String title;
    private String body;
    private List<PostCommentModel> comments;

}
