package com.example.gateway.controller;

import com.example.gateway.client.UserClient;
import com.example.gateway.config.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authorization")
@RequiredArgsConstructor
public class AuthController {
  private static final String TOKEN_NAME = "JWT";
  private final JwtProvider jwtProvider;
  private final UserClient userClient;


}
