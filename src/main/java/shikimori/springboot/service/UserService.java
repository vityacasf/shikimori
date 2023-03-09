package shikimori.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shikimori.springboot.model.User;
import shikimori.springboot.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  public User save(final User user) {
    return userRepository.save(user.getLogin(), user.getPassword(), user.getRoleId());
  }
}
