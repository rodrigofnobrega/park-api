package com.rodrigofnobrega.demoparkapi;

import com.rodrigofnobrega.demoparkapi.web.dto.UserCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.UserResponseDto;
import com.rodrigofnobrega.demoparkapi.web.exception.ErrorMessage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.assertj.core.api.Assertions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/users/users-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/users/users-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserIT {
	@Autowired
	WebTestClient webTestClient;
	
	@LocalServerPort
	private int port = 8081;

	@Test
	public void createUser_WithUsernameAndPasswordValid_ReturnUserCreateWithStatus201() {
		UserResponseDto responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("james@email.com", "123456"))
				.exchange()
				.expectStatus().isCreated()
				.expectBody(UserResponseDto.class)
				.returnResult().getResponseBody();
		

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getId()).isNotNull();
		Assertions.assertThat(responseBody.getUsername()).isEqualTo("james@email.com");
		Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
	}
	
	@Test
	public void createUser_WithUsernameInalid_ReturnErrorMessageStatus422() {
		ErrorMessage responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("", "123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto(" ", "123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("bob@", "123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("bob@email", "123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("bob@email.", "123456"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
	}
	
	@Test
	public void createUser_WithPasswordInvalid_ReturnErrorMessageStatus422() {
		ErrorMessage responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("bob@email.com", ""))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("bob@email.com", "12345"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = webTestClient
				.post()
				.uri(String.format("http://127.0.0.1:%d/api/v1/usuarios", port))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("bob@email.com", "1234567"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);	
	}
}
