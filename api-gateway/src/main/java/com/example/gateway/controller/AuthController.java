package com.example.gateway.controller;

import com.example.gateway.client.UserClient;
import com.example.gateway.config.security.jwt.JwtProvider;
import com.example.gateway.dto.auth.AuthorizationUserRequest;
import com.example.gateway.dto.user.GetUserByCredentialsRequest;
import com.example.gateway.dto.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/authorization")
@RequiredArgsConstructor
public class AuthController {
  private static final String TOKEN_NAME = "TOKEN";
  private final JwtProvider jwtProvider;
  private final UserClient userClient;

  @PostMapping("/login")
  public void login(
      @RequestBody AuthorizationUserRequest request,
      HttpServletResponse response
  ) {
    GetUserByCredentialsRequest getUserByCredentialsRequest =
        GetUserByCredentialsRequest
            .builder()
            .login(request.getLogin())
            .password(request.getPassword())
            .build();

    Optional<UserResponse> userResponse =
        userClient.getByCredentials(getUserByCredentialsRequest);
    if (userResponse.isPresent()) {
      UserResponse user = userResponse.get();

      String token = jwtProvider.generateToken(
          user.getId(),
          user.getLogin(),
          user.getRoleName()
      );
      final Cookie cookie = new Cookie(TOKEN_NAME, token);
      cookie.setPath("/");
      cookie.setHttpOnly(true);
      response.addCookie(cookie);

      response.setStatus(HttpStatus.OK.value());
    } else {
      response.setStatus(HttpStatus.CONFLICT.value());
    }

  }
}
