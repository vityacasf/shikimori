package shikimori.springboot.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import shikimori.springboot.model.User;

public interface UserRepository extends Repository<User, Long> {
  @Query("INSERT INTO users (login, password, role_id) VALUES(:login, :password, :rolId) " +
      "RETURNING id, login, password, role_id")
  User save(@Param("login") final String login,
            @Param("password") final String password,
            @Param("roleId") final Integer roleId);
}
