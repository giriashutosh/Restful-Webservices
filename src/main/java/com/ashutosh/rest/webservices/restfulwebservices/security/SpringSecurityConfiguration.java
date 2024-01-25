package com.ashutosh.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //1.All request should be authenticated
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        //2. else show web page
        http.httpBasic(Customizer.withDefaults());
        //3.disable csrf
        http.csrf().disable();
        return http.build();
    }
}
