package com.grocery.app.Service.SecurityService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults()) // CORS defaults
                .csrf(csrf -> csrf.ignoringRequestMatchers("/sign-in/register", "/Admin/**","/Order")) // Ignore CSRF for Admin endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/sign-in/register", "/Admin/**","/Order").permitAll() // Public APIs for Admin and its sub-paths
                        .requestMatchers(HttpMethod.DELETE, "/Admin/**").authenticated() // DELETE requires authentication
                        .requestMatchers(HttpMethod.POST,"/Order").authenticated()
                        .anyRequest().authenticated() // Protect all other APIs
                )
                .httpBasic(Customizer.withDefaults()); // Enable Basic Authentication

        return http.build();
    }
}
