package shikimori.springboot.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import shikimori.springboot.model.Club;

import java.util.List;
import java.util.Optional;

public interface ClubRepository extends Repository<Club, Long> {
  Club save(final Club club);

  @Query("SELECT * FROM club WHERE name:=name")
  Optional<Club> getByName(@Param("name") final String name);

  @Query("SELECT * FROM club WHERE id=:groupId")
  Optional<Club> getById(@Param("groupId") final Long groupId);

  @Query("SELECT * FROM club")
  List<Club> getGroupList();
}
