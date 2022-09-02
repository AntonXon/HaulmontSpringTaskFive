package com.example.springrest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((http -> {
            http
                    .antMatchers(HttpMethod.GET, "/users").hasAnyRole("VIEWER", "FULL")
                    .antMatchers(HttpMethod.GET, "/users/**").hasAnyRole("VIEWER", "FULL")
                    .antMatchers(HttpMethod.PUT, "/users/**").hasRole("FULL")
                    .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("FULL")
                    .antMatchers(HttpMethod.POST, "/users").hasRole("FULL");
        })).csrf().disable().httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager detailsManager() {
        UserDetails user1 = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password1")
                .roles("FULL")
                .build();
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user2")
                .password("password2")
                .roles("VIEWER")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
}
