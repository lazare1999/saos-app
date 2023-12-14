package com.lazo.saos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		servers = {
				@Server(url = "/", description = "Default Server URL"),
				@Server(url = "https://saosapp-production.up.railway.app/", description = "railway Server URL"),
				@Server(url = "https://saos.lazarekvirtia.com/", description = "saos.lazarekvirtia Server URL")
		}
)
@SpringBootApplication
public class SaosAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaosAppApplication.class, args);
	}

}
