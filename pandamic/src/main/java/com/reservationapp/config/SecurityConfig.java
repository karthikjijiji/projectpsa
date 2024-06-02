package com.reservationapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.access.intercept.AuthorizationFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    @Bean
    protected void configure(HttpSecurity http)  {

        http.csrf().disable().cors().disable();
        http.addFilterBefore((jwtRequestFilter, AuthorizationFilter.class))
        http.authorizeHttpRequests()
                .requestMatchers("api/v1/users/addUser","api/v1/users/login").permitAll()
                .requestMatchers("/api/v1/users/addCountry").hasRole("ADMIN")
                .requestMatchers("/api/v1/users/Profile").hasAnyRole()("USER","ADMIN")
                .anyRequest().authenticated();

        return http.build();
    }
}
