package com.example.demo.web.controllers.dtos;

import com.example.demo.common.ResourceReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class CreatePostRequestJsonTest {

    @Autowired
    private JacksonTester<CreatePostRequest> json;

    @Test
    void serializationTest() throws IOException {
        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .title("title here")
                .body("body here")
                .build();

        JsonContent<CreatePostRequest> result = json.write(createPostRequest);

        assertThat(result).isStrictlyEqualToJson("create-post-request-single.json");
        assertThat(result).hasJsonPathStringValue("@.title");
        assertThat(result).extractingJsonPathStringValue("@.title")
                .isEqualTo("title here");
        assertThat(result).hasJsonPathStringValue("@.body");
        assertThat(result).extractingJsonPathStringValue("@.body")
                .isEqualTo("body here");
    }

    @Test
    void deserializationTest() throws IOException {
        String createPostRequestAsString = ResourceReader.readFileToString("classpath:com/example/demo/web/controllers/dtos/create-post-request-single.json");
        CreatePostRequest createPostRequest = CreatePostRequest.builder()
                .title("title here")
                .body("body here")
                .build();

        assertThat(json.parse(createPostRequestAsString))
                .isEqualTo(createPostRequest);
        CreatePostRequest parsedCreatePostRequest = json.parseObject(createPostRequestAsString);
        assertThat(parsedCreatePostRequest.getTitle()).isEqualTo("title here");
        assertThat(parsedCreatePostRequest.getBody()).isEqualTo("body here");
    }

}