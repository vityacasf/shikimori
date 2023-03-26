package com.example.gateway.client;

import com.example.gateway.dto.user.GetUserByCredentialsRequest;
import com.example.gateway.dto.user.SaveUserRequest;
import com.example.gateway.dto.user.SaveUserResponse;
import com.example.gateway.dto.user.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;

@FeignClient(name = "user", url = "${services.user.url}/api/v1")
public interface UserClient {
  //UserController
  @PostMapping("/user/credentials")
  Optional<UserResponse> getByCredentials(@RequestBody GetUserByCredentialsRequest request);

  @GetMapping("/user/{userId}")
  ResponseEntity<UserResponse> getById(@PathVariable("userId") final Long userId);

  @PostMapping("/users")
  ResponseEntity<SaveUserResponse> saveUser(@RequestBody SaveUserRequest request);
}
