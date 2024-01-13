package com.rodrigofnobrega.demoparkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoParkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoParkApiApplication.class, args);
	}
}
