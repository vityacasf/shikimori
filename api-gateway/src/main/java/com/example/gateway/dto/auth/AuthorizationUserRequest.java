package com.example.gateway.dto.auth;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class AuthorizationUserRequest {
  String login;
  String password;
}
