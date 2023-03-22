package shikimori.springboot.model;

import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Builder
@Value
@Table("role")
public class Role {
  @Id
  Long id;
  String name;
}
