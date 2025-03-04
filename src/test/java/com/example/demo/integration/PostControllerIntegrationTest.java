package com.example.demo.integration;

import com.example.demo.domain.jpa.PostEntity;
import com.example.demo.repositories.PostRepository;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;

public class PostControllerIntegrationTest extends BaseIntegration {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void setUp() {
        postRepository.deleteAll();
    }

    @Test
    void shouldLoadPosts() {
        stubFor(get("/posts").willReturn(ok("""
                [
                  {
                    "id": 1,
                    "title": "Title 1",
                    "body": "Body 1"
                  }
                ]
                """)
                .withHeader("Content-Type", "application/json")
        ));
        stubFor(get("/posts/1/comments").willReturn(ok("""
                [
                  {
                    "postId": 1,
                    "id": 1,
                    "name": "Name 1",
                    "email": "email1@example.com",
                    "body": "Body 1"
                  }
                ]
                """)
                .withHeader("Content-Type", "application/json")
        ));

        ResponseEntity<Void> response = restTemplate
                .getForEntity("/api/v1/posts/load", Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        List<PostEntity> foundPosts = postRepository.findByTitleLikeIgnoreCase("title 1");
        assertThat(foundPosts).hasSize(1);
        PostEntity foundPostEntity = foundPosts.getFirst();
        assertThat(foundPostEntity.getTitle()).isEqualTo("Title 1");
        assertThat(foundPostEntity.getBody()).isEqualTo("Body 1");
    }

    @Test
    void shouldReturnPageWithSortedPosts() {
        postRepository.save(
                PostEntity.builder()
                        .title("Title 2")
                        .body("Body 2")
                        .build()
        );
        postRepository.save(
                PostEntity.builder()
                        .title("Title 1")
                        .body("Body 1")
                        .build()
        );
        postRepository.save(
                PostEntity.builder()
                        .title("Title 3")
                        .body("Body 3")
                        .build()
        );

        ResponseEntity<String> response = restTemplate.getForEntity("/api/v1/posts?page=0&size=1&sort=title,desc", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray read = documentContext.read("$[*]");
        assertThat(read.size()).isEqualTo(1);

        String title = documentContext.read("$[0].title");
        assertThat(title).isEqualTo("Title 3");
    }

}
