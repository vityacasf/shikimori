package shikimori.springboot.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import shikimori.springboot.model.User;
import shikimori.springboot.model.UserWithRole;
import shikimori.springboot.service.UserService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserFacade {
  private final UserService userService;

  public Optional<User> save(final User user) {
    Optional<User> findUser = userService.getUserByLogin(user.getLogin());

    if (findUser.isEmpty()) {
      User savedUser = userService.save(user);
      return Optional.of(savedUser);
    }
    return Optional.empty();
  }

  public Optional<UserWithRole> getById(final Long userId) {
    return userService.getUserById(userId);
  }

  public Optional<UserWithRole> getByCredentials(final User user) {
    return userService.getByCredentials(user);
  }
}
