package com.example.demo.web.controllers.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UpdatePostRequest {

    private String title;
    private String body;

}
