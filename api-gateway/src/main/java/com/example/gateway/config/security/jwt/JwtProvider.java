package com.example.gateway.config.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
  private final String jwtSecret;
  private final Long sessionDurationMinutes;

  public JwtProvider(
      @Value("${security.secret}") final String jwtSecret,
      @Value("${security.session-duration-minutes}") final Long sessionDurationMinutes
  ){
    this.jwtSecret = jwtSecret;
    this.sessionDurationMinutes = sessionDurationMinutes;
  }

  public String generateToken(final Long id, final String login, final String roleName) {
    final Instant instant = LocalDateTime.now()
        .plus(Duration.ofMinutes(sessionDurationMinutes))
        .toInstant(ZoneOffset.UTC);
    final Date date = Date.from(instant);
    return Jwts.builder()
        .setSubject(id.toString())
        .claim("login", login)
        .claim("roleName", roleName)
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expEx) {
      log.info("Jwt expired exception");
    } catch (UnsupportedJwtException unsEx) {
      log.info("Unsupported jwt exception");
    } catch (MalformedJwtException mjEx) {
      log.info("Malformed jwt exception");
    } catch (SignatureException sEx) {
      log.info("Signature exception");
    } catch (Exception ex) {
      log.info("Invalid token");
    }
    return false;
  }

  public Claims getTokenClaims(final String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
  }
}
