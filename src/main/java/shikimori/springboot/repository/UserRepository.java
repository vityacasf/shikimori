package shikimori.springboot.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import shikimori.springboot.model.User;

public interface UserRepository extends Repository<User, Long> {
  @Query("INSERT INTO users (login, password, role) VALUES(:login, :password, :role) " +
      "RETURNING id, login, password, role")
  User save(@Param("login") final String login,
            @Param("password") final String password,
            @Param("role") final String role);
}
