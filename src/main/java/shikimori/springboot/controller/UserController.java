package shikimori.springboot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shikimori.springboot.converter.UserConverter;
import shikimori.springboot.dto.user.SaveUserRequest;
import shikimori.springboot.facade.UserFacade;
import shikimori.springboot.model.User;
import shikimori.springboot.model.UserWithRole;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
  private final UserFacade userFacade;
  private final UserConverter userConverter;

  @PostMapping("/user")
  ResponseEntity save(@RequestBody SaveUserRequest request){
    User userFromRequest = userConverter.fromDto(request);
    Optional<User> savedUser = userFacade.save(userFromRequest);

    return savedUser
        .map(user -> new ResponseEntity(userConverter.toDto(user), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.CONFLICT));
  }

  @GetMapping("/user/{userId}")
  ResponseEntity getById(@PathVariable("userId") final Long userId) {
    Optional<UserWithRole> foundUser = userFacade.getById(userId);

    return foundUser
        .map(user -> new ResponseEntity(userConverter.toDto(user), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity(HttpStatus.CONFLICT));
  }
}
