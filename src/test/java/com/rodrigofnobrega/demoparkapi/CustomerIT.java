package com.rodrigofnobrega.demoparkapi;

import com.rodrigofnobrega.demoparkapi.web.dto.customer.CustomerCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.customer.CustomerResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Sql(scripts = "/sql/customers/customers-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/customers/customers-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CustomerIT {
//    @Autowired
//    private WebTestClient testClient;

    @LocalServerPort
    private int port = 8080;

    @Test
    public void crateCustomer_WithValidDatas_ReturnCustomerWithStatus201() {
        WebTestClient testClient = WebTestClient.bindToServer().baseUrl("http://127.0.0.1:" + port).build();

        CustomerResponseDto responseBody = testClient
                .post()
                .uri("/api/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(JwtAuthentication.getHeaderAuthorization(testClient, "toby@email.com", "123456"))
                .bodyValue(new CustomerCreateDto("Tobias Ferreira", "09961838092"))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CustomerResponseDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.getId()).isNotNull();
        Assertions.assertThat(responseBody.getName()).isEqualTo("Tobias Ferreira");
        Assertions.assertThat(responseBody.getCpf()).isEqualTo("09961838092");
    }
}
