package com.example.gateway.controller;

import com.example.gateway.client.UserClient;
import com.example.gateway.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {
  private final UserClient userClient;

  @GetMapping
  public ResponseEntity<UserResponse> getCurrentUser() {
    final DefaultOAuth2User principal = (DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    final Long userId = Long.valueOf(principal.getName());
    return userClient.getById(userId);
  }
}
