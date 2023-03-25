package com.example.gateway.config.security.jwt;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtProvider {
  private String jwtSecret;

  public JwtProvider(
      @Value("${security.secret}") final String jwtSecret){
    this.jwtSecret = jwtSecret;
  }
}
