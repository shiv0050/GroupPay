package com.example.GroupPay.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableWebSecurity
public class MultiFilterSecurityConfig {

    @Autowired
    UserAuthFilter userAuthFilter;

    @Bean
    public SecurityFilterChain configure (HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/user/**")
                            .permitAll()
                            .requestMatchers("/order/create")
                            .permitAll()
                            .anyRequest().authenticated()
                )
                .addFilterAfter(userAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy((SessionCreationPolicy.STATELESS)))
                ;

        http.exceptionHandling(exception -> exception.authenticationEntryPoint((request, res, e) -> {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> response = new HashMap<>();
            response.put("timestamp", LocalDateTime.now().toString());
            response.put("message", "Invalid/Missing/Expired AuthToken");
            res.setContentType("application/json;charset=UTF-8");
            res.setStatus(401);
            res.getWriter().write(mapper.writeValueAsString(response));
        }));

        return http.build();
    }
}
