package com.example.MicroVenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroVentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroVentaApplication.class, args);
	}

}
