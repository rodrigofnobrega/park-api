package com.rodrigofnobrega.demoparkapi;

import com.rodrigofnobrega.demoparkapi.jwt.JwtToken;
import com.rodrigofnobrega.demoparkapi.web.dto.UserLoginDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = "/sql/users/users-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/users/users-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AuthenticationIT {
    @LocalServerPort
    private int port = 8080;

    @Test
    public void authenticate_WithValidCredentials_ReturnTokenWithStatus200() {
        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();

        JwtToken jwtToken = webTestClient
                .post()
                .uri("/api/v1/auth")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new UserLoginDto("ana@email.com", "123456"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(JwtToken.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(jwtToken).isNotNull();
    }
}
