package com.example.demo.web.controllers.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CreatePostRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String body;

}
