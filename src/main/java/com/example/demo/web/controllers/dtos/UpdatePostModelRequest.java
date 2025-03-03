package com.example.demo.web.controllers.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class UpdatePostModelRequest {

    private String title;
    private String body;

}
