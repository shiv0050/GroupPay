package com.example.GroupPay.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JWTUtil implements Serializable, EnvironmentAware {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60;
    private String secret;
    private final String ISSUER = "dev.GroupPay.user-auth";

    private Claims getClaimsFromToken(String token) {
        System.out.println("JWT Secret Claim: " + secret);

        Claims claims = Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();

        if(!validateToken(claims)){
            throw new MalformedJwtException("JWT not issued!");
        }

        return claims;
    }

    private Boolean isTokenExpired(Claims claims) {
        final Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public Boolean validateToken(Claims claims) {
        if(isTokenExpired(claims)){
            throw new MalformedJwtException("Jwt Token expired");
        }

        return claims.get("Issuer").equals(ISSUER);
    }

    public String generateToken(String subject) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Issuer", ISSUER);
        System.out.println("JWT Secret: " + secret);

        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

//    private Key getSignInKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secret);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    @Override
    public void setEnvironment(Environment environment) {
        this.secret = environment.getProperty("jwt.secret");
    }

}
