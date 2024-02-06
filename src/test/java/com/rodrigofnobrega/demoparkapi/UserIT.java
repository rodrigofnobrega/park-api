package com.rodrigofnobrega.demoparkapi;

import com.rodrigofnobrega.demoparkapi.web.dto.user.UserCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.user.UserPasswordDto;
import com.rodrigofnobrega.demoparkapi.web.dto.user.UserResponseDto;
import com.rodrigofnobrega.demoparkapi.web.exception.ErrorMessage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import org.assertj.core.api.Assertions;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = "/sql/users/users-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/users/users-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserIT {
	@LocalServerPort
	private int port = 8080;
	
	@Test
	public void createUser_WithUsernameAndPasswordValid_ReturnUserCreateWithStatus201() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		UserResponseDto responseBody = webTestClient
				.post()
				.uri("/api/v1/usuarios")
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
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		ErrorMessage responseBody = webTestClient
				.post()
				.uri("/api/v1/usuarios")
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
				.uri("/api/v1/usuarios")
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
				.uri("/api/v1/usuarios")
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
				.uri("/api/v1/usuarios")
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
				.uri("/api/v1/usuarios")
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
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		ErrorMessage responseBody = webTestClient
				.post()
				.uri("/api/v1/usuarios")
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
				.uri("/api/v1/usuarios")
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
				.uri("/api/v1/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("bob@email.com", "1234567"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);	
	}
	
	@Test
	public void createUser_WithRepeatedUsername_ReturnErrorMessageWithStatus409() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		ErrorMessage responseBody = webTestClient
				.post()
				.uri("/api/v1/usuarios")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserCreateDto("ana@email.com", "123456"))
				.exchange()
				.expectStatus().isEqualTo(409)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(409);
	}
	
	@Test
	public void searchUser_WithValidId_ReturnUserWithStatus200() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();

		int userId = 100;
		
		UserResponseDto responseBody = webTestClient
				.get()
				.uri("/api/v1/usuarios/" + userId)
				.headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(UserResponseDto.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getId()).isEqualTo(userId);
		Assertions.assertThat(responseBody.getUsername()).isEqualTo("ana@email.com");
		Assertions.assertThat(responseBody.getRole()).isEqualTo("ADMIN");

		userId = 101;

		 responseBody = webTestClient
				.get()
				.uri("/api/v1/usuarios/" + userId)
				.headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(UserResponseDto.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getId()).isEqualTo(userId);
		Assertions.assertThat(responseBody.getUsername()).isEqualTo("bia@email.com");
		Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");

		responseBody = webTestClient
				.get()
				.uri("/api/v1/usuarios/" + userId)
				.headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "bia@email.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBody(UserResponseDto.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getId()).isEqualTo(userId);
		Assertions.assertThat(responseBody.getUsername()).isEqualTo("bia@email.com");
		Assertions.assertThat(responseBody.getRole()).isEqualTo("CLIENTE");
	}

	@Test
	public void searchUser_WithUserClientFindOtherClient_ReturnErrorMessageWithStatus403() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();

		int user_id = 102;

		ErrorMessage responseBody = webTestClient
				.get()
				.uri("/api/v1/usuarios/" + user_id)
				.headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "bia@email.com", "123456"))
				.exchange()
				.expectStatus().isForbidden()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
	}
	@Test
	public void searchUser_WithInvalidId_ReturnErrorMessageWithStatus404() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();

		int user_id = 0;

		ErrorMessage responseBody = webTestClient
				.get()
				.uri("/api/v1/usuarios/" + user_id)
				.headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(404);
	}

	@Test
	public void editPassword_WithValidDatas_ReturnStatus204() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		int id = 100;
		
		webTestClient
				.patch()
				.uri("/api/v1/usuarios/" + id)
				.headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDto("123456", "123456", "123456"))
				.exchange()
				.expectStatus().isNoContent();

		id = 101;

		webTestClient
				.patch()
				.uri("/api/v1/usuarios/" + id)
				.headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "bia@email.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDto("123456", "123456", "123456"))
				.exchange()
				.expectStatus().isNoContent();
	}
	
	@Test
	public void editPassword_WithDifferentUsers_ReturnErrorMessageWithStatus403() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		int id = 0;
		
		ErrorMessage responseBody = webTestClient
				.patch()
				.uri("/api/v1/usuarios/" + id)
				.contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.bodyValue(new UserPasswordDto("123456", "112233", "112233"))
				.exchange()
				.expectStatus().isForbidden()
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);

        responseBody = webTestClient
                .patch()
                .uri("/api/v1/usuarios/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "bia@email.com", "123456"))
                .bodyValue(new UserPasswordDto("123456", "112233", "112233"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
	}
	
	@Test
	public void editPassword_WithInvalidFields_ReturnErrorMessageWithStatus422() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		int id = 100;
		
		ErrorMessage responseBody = webTestClient
				.patch()
				.uri("/api/v1/usuarios/" + id)
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDto("", "", ""))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = webTestClient
				.patch()
				.uri("/api/v1/usuarios/" + id)
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDto("12345", "12345", "12345"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
		
		responseBody = webTestClient
				.patch()
				.uri("/api/v1/usuarios/" + id)
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDto("1234567", "1234567", "1234567"))
				.exchange()
				.expectStatus().isEqualTo(422)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(422);
	}
	
	@Test
	public void editPassword_WithInvalidPassword_ReturnErrorMessageWithStatus400() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		int id = 100;
		
		ErrorMessage responseBody = webTestClient
				.patch()
				.uri("/api/v1/usuarios/" + id)
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDto("123456", "123456", "000000"))
				.exchange()
				.expectStatus().isEqualTo(400)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);
		
		responseBody = webTestClient
				.patch()
				.uri("/api/v1/usuarios/" + id)
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(new UserPasswordDto("000000", "123456", "123456"))
				.exchange()
				.expectStatus().isEqualTo(400)
				.expectBody(ErrorMessage.class)
				.returnResult().getResponseBody();

		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody.getStatus()).isEqualTo(400);
	}
	
	@Test
	public void listAllUsers_WithoutAnyParameters_ReturnUserListOfUsersWithStatus200() {
		WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();	
		
		List<UserResponseDto> responseBody = webTestClient
				.get()
				.uri("/api/v1/usuarios")
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "ana@email.com", "123456"))
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(UserResponseDto.class)
				.returnResult()
				.getResponseBody();
		
		UserResponseDto ana = new UserResponseDto();
		ana.setId((long) 100);
		ana.setUsername("ana@email.com");
		ana.setRole("ADMIN");
		
		UserResponseDto bia = new UserResponseDto();
		bia.setId((long) 101);
		bia.setUsername("bia@email.com");
		bia.setRole("CLIENTE");
		
		UserResponseDto bob = new UserResponseDto();
		bob.setId((long) 102);
		bob.setUsername("bob@email.com");
		bob.setRole("CLIENTE");
		
		Assertions.assertThat(responseBody).isNotNull();
		Assertions.assertThat(responseBody).hasSize(3);
		Assertions.assertThat(responseBody).contains(ana);
		Assertions.assertThat(responseBody).contains(bia);
		Assertions.assertThat(responseBody).contains(bob);
	}

    @Test
    public void listAllUsers_WithUserWithoutPermission_ReturnErrorMessageWithStatus403() {
        WebTestClient webTestClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();

        ErrorMessage responseBody = webTestClient
                .get()
                .uri("/api/v1/usuarios")
                .headers(JwtAuthentication.getHeaderAuthorization(webTestClient, "bia@email.com", "123456"))
                .exchange()
                .expectStatus().isForbidden()
                .expectBody(ErrorMessage.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getStatus()).isEqualTo(403);
    }


}
