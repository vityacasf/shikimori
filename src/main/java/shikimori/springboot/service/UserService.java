package shikimori.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shikimori.springboot.model.User;
import shikimori.springboot.model.UserWithRole;
import shikimori.springboot.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  public User save(final User user) {
    return userRepository.save(user.getLogin(), user.getPassword(), user.getRoleId());
  }

  public Optional<User> getUserByLogin(final String login) {
    return userRepository.getByLogin(login);
  }

  public Optional<UserWithRole> getUserById(final Long userId) {
    return userRepository.getByUserId(userId);
  }
}
