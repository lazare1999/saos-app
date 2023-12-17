package com.lazo.saos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@OpenAPIDefinition(
		servers = {
				@Server(url = "/", description = "Default Server URL"),
				@Server(url = "https://saosapp-production.up.railway.app/", description = "Railway Server URL"),
				@Server(url = "https://saos.lazarekvirtia.com/", description = "saos.lazarekvirtia Server URL")
		}
)
@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class, ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
public class SaosAppApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SaosAppApplication.class, args);
	}

}
