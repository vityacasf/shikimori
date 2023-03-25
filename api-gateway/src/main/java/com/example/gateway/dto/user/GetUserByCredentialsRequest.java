package com.example.gateway.dto.user;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class GetUserByCredentialsRequest {
  String login;
  String password;
}
