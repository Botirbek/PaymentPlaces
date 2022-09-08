package com.example.paymentplaces.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    public final static String[] WHITE_LIST = {
            "/error",
            "/api/login",
            "/auth/access/token",
            "/auth/refresh/token",
            "/auth/register",
            "/swagger-ui/**",
            "/api-docs/**",
            "/**"
    };


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(
                        (authorize)-> authorize.antMatchers(WHITE_LIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .x509(Customizer.withDefaults());

        return http.build();

    }

}
