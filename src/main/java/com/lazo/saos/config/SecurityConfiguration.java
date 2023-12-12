//package com.lazo.saos.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * Created by Lazo on 2023-12-10
// */
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration {
//
//    @Value("${saos.module.user}")
//    private String USER;
//
//    @Value("${saos.module.password}")
//    private String PASSWORD;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        // Allow access to Swagger
//                        .requestMatchers(
//                                "/v0/api-docs/**",
//                                "/swagger-ui/**",
//                                "/swagger-ui.html"
//                        ).permitAll()
//                        // Authenticate all other requests
//                        .anyRequest().authenticated()
//
//                )
//                .formLogin(Customizer.withDefaults())
//                .logout(Customizer.withDefaults())
//                // Use basic authentication (user/pass)
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails userDetails = User.builder()
//                .username(USER)
//                .password(passwordEncoder().encode(PASSWORD))
//                .build();
//
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
