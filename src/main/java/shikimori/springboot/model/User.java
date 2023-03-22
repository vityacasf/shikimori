package shikimori.springboot.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Value
@Builder
@Table("users")
public class User {
  @Id
  @Column("id")
  Long id;
  @Column("login")
  String login;
  @Column("name")
  String name;
  @Column("email")
  String email;
  @Column("password")
  String password;
  @Column("role_id")
  Integer roleId;
}
