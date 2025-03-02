package com.example.demo.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PostDto {

    private Long id;
    private String title;
    private String body;

}
