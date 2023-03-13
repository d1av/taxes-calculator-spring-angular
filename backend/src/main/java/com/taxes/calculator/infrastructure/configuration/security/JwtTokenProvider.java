package com.taxes.calculator.infrastructure.configuration.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.taxes.calculator.domain.exceptions.NotificationException;
import com.taxes.calculator.domain.validation.Error;
import com.taxes.calculator.domain.validation.handler.Notification;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpirationDate;

    // generate JWT token
    public String generateToken(Authentication authentication) {
	String username = authentication.getName();

	Date currentDate = new Date();

	Date expireDate = new Date(
		currentDate.getTime() + jwtExpirationDate);

	return Jwts.builder().setSubject(username)
		.setIssuedAt(new Date())
		.setExpiration(expireDate).signWith(key())
		.compact();
    }

    private Key key() {
	return Keys.hmacShaKeyFor(
		Decoders.BASE64.decode(jwtSecret));
    }

    // get username from Jwt token
    public String getUsername(String token) {
	Claims claims = Jwts.parserBuilder().setSigningKey(key())
		.build().parseClaimsJws(token).getBody();
	String username = claims.getSubject();
	return username;
    }

    // validate Jwt token
    public boolean validateToken(String token) {
	Notification notification = Notification.create();
	try {
	    Jwts.parserBuilder().setSigningKey(key()).build()
		    .parse(token);
	    return true;
	} catch (MalformedJwtException ex) {
	    notification.append(new Error("Token mal formado"));
	    throw new NotificationException("Erro no Token JWT",
		    notification);
	} catch (ExpiredJwtException ex) {
	    notification.append(new Error("Invalid JWT tokens"));
	    throw new NotificationException("Erro no Token JWT",
		    notification);
	} catch (UnsupportedJwtException ex) {
	    notification
		    .append(new Error("Unsupported JWT token"));
	    throw new NotificationException("Erro no Token JWT",
		    notification);
	} catch (IllegalArgumentException ex) {
	    notification.append(
		    new Error("JWT claims string is empty."));
	    throw new NotificationException("Erro no Token JWT",
		    notification);
	}
    }
}