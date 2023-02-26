package com.taxes.calculator.infrastructure.configuration.security;

import java.security.Key;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.taxes.calculator.domain.validation.Error;

import com.taxes.calculator.domain.exceptions.DomainException;

@Component
public class JwtTokenProvider {

    private String jwtSecret = "2a10PpT8k8VQjaseDzyUg4HIYe8sR4gBKiQ1SdP1UDDGcwG6zZc4XWGBK";

    private Long jwtExpirationDate = 11233112212121212l;

    // generate JWT token
    public String generateToken(Authentication authentication) {
	String username = authentication.getName();

	Date currentDate = new Date();

	Date expireDate = new Date(
		currentDate.getTime() + jwtExpirationDate);

	String token = Jwts.builder().setSubject(username)
		.setIssuedAt(new Date()).setExpiration(expireDate)
		.signWith(key()).compact();
	return token;
    }

    private Key key() {
	return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // get username from Jwt token
    public String getUsername(String token) {
	Claims claims = Jwts.parserBuilder().setSigningKey(key()).build()
		.parseClaimsJws(token).getBody();
	String username = claims.getSubject();
	return username;
    }

    // validate Jwt token
    public boolean validateToken(String token) {
	try {
	    Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
	    return true;
	} catch (MalformedJwtException ex) {
	    throw DomainException.with(new Error("Invalid JWT token"));
	} catch (ExpiredJwtException ex) {
	    throw DomainException.with(new Error("Invalid JWT token"));
	} catch (UnsupportedJwtException ex) {
	    throw DomainException.with(new Error("Unsupported JWT token"));
	} catch (IllegalArgumentException ex) {
	    throw DomainException
		    .with(new Error("JWT claims string is empty."));
	}
    }
}