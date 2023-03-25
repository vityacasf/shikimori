package shikimori.springboot.converter;

import org.mapstruct.Mapper;
import shikimori.springboot.dto.user.GetUserByCredentialsRequest;
import shikimori.springboot.dto.user.SaveUserRequest;
import shikimori.springboot.dto.user.SaveUserResponse;
import shikimori.springboot.dto.user.UserResponse;
import shikimori.springboot.model.User;
import shikimori.springboot.model.UserWithRole;

@Mapper
public interface UserConverter {
  static UserResponse toDto(final UserWithRole user);
  SaveUserResponse toDto(final User user);

  User fromDto(final SaveUserRequest user);

  static User fromDto(final GetUserByCredentialsRequest request);
}
