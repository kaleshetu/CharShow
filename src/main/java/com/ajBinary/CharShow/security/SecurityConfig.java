package com.ajBinary.CharShow.security;

import com.ajBinary.CharShow.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

//Configure my Security Filter Bean
@Configuration
@EnableWebSecurity

public class SecurityConfig {
    @Autowired
    private UserServiceImpl userService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf((c)->{c.disable();})
                .cors((c)->Customizer.withDefaults())
                .authorizeHttpRequests(//Authenticate all requests
                        (c)->{c.requestMatchers(HttpMethod.GET,
                                        "api/v1/cars/*").hasAnyRole("ADMIN","USER")
                                .requestMatchers(HttpMethod.POST,
                                        "api/v1/cars/*").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE,
                                        "api/v1/cars/*").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,
                                        "api/v1/cars/*").hasRole("ADMIN")
                                .anyRequest().authenticated();
                        })
                .httpBasic(Customizer.withDefaults())//Basic configuration with web defaults.
                .build();



    }
   /* @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(bCryptPasswordEncoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }*/
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}