package com.lingua.srsbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 1. Disable CSRF so external Python scripts can make POST/PUT requests safely
            .csrf(csrf -> csrf.disable())
            
            // 2. Authorize incoming network requests
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/flashcards/**",
                    "/api/flashcards/**",  // Matches endpoints with IDs like /api/flashcards/1
                    "/error"
                ).permitAll() // Allow everyone to use the flashcard endpoints
                .anyRequest().authenticated()                      // Secure everything else
            );

        return http.build();
    }
}
