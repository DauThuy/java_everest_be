package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.entity.Account;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class ProvideJwt {
    @Value("secret")
    private String jwtSecret;

    @Value("64800000")
    private String jwtExpirationInMs;

    public String generateTokenForEmployee(Account auth) {
        return JWT.create()
                .withSubject(Integer.toString(auth.getAccountId()))
                .withClaim("id", auth.getAccountId())
                .withExpiresAt(new Date(System.currentTimeMillis()  + Integer.parseInt(jwtExpirationInMs)))
                .sign(Algorithm.HMAC512(jwtSecret.getBytes()));
    }

    public Integer getUserIdFromJWT (String token) {
        String id = JWT
                .require(Algorithm.HMAC512(jwtSecret.getBytes()))
                .build()
                .verify(token)
                .getSubject();

        return Integer.parseInt(id);
    }

//    public Integer getUserIdFromJWT(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(jwtSecret)
//                .parseClaimsJws(token)
//                .getBody();
//
//        return  Integer.parseInt(claims.getSubject());
//    }

    public String validateToken (String accessToken) {
        return JWT
                .require(Algorithm.HMAC512(jwtSecret.getBytes()))
                .build()
                .verify(accessToken)
                .getPayload();
    }

//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//            return true;
//        } catch (MalformedJwtException ex) {
//            log.error("Invalid JWT token");
//        } catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
//        } catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
//        } catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
//        }
//        return false;
//    }

}
