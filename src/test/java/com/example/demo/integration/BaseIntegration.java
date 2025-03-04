package com.example.demo.integration;

import com.example.demo.TestcontainersConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.wiremock.spring.EnableWireMock;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.cloud.openfeign.client.config.jsonplaceholder.url=${wiremock.server.baseUrl}"
        }
)
@ActiveProfiles("test")
@Import(TestcontainersConfiguration.class)
@EnableWireMock
public class BaseIntegration {
}
