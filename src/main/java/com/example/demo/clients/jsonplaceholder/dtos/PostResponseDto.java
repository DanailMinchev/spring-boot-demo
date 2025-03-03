package com.example.demo.clients.jsonplaceholder.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PostResponseDto {

    private Long id;
    private String title;
    private String body;

}
