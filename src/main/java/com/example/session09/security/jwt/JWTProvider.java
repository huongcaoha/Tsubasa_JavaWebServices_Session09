package com.example.session09.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTProvider {
    String secret = "huongcaoha";
    long expirationTime = 15 * 60 * 1000;// 15 phút

    public String generateToken(String email) {
        Date expriredDate = new Date(new Date().getTime() + expirationTime);
        return Jwts
                .builder()
                .setSubject(email)
                .setExpiration(expriredDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    public boolean validateToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody() != null;
    }

    public String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }
}
