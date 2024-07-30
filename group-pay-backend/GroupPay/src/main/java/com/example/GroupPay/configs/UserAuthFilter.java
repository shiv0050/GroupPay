package com.example.GroupPay.configs;

import com.example.GroupPay.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@Slf4j
public class UserAuthFilter extends OncePerRequestFilter {

    private final String HEADER_STRING = "UserToken";
    @Autowired
    JWTUtil jwtUtil;

    public Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);

        try {
            if (token != null) {
                return new UsernamePasswordAuthenticationToken(
                        jwtUtil.getUserFromToken(token), null, Collections.emptyList());
            }
        } catch (ExpiredJwtException exception) {
            log.error("Request to parse expired JWT: Failed: "+exception.getMessage());
        } catch (UnsupportedJwtException exception) {
            log.error("Request to parse unsupported JWT: Failed: "+exception.getMessage());
        } catch (MalformedJwtException exception) {
            log.error("Request to parse invalid JWT: Failed: "+exception.getMessage());
        } catch (SignatureException exception) {
            log.error("Request to parse JWT with invalid signature: Failed: "+exception.getMessage());
        } catch (IllegalArgumentException exception) {
            log.error("Request to parse empty or null JWT: Failed: "+exception.getMessage());
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = getAuthentication(request);

        if (authentication == null) {
            filterChain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request,response);
    }
}
