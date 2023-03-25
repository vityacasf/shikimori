package shikimori.springboot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shikimori.springboot.model.Club;
import shikimori.springboot.repository.ClubRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClubService {
  private final ClubRepository clubRepository;

  public Club save(final Club club) {
    return clubRepository.save(club);
  }

  public Optional<Club> getByName(final String name) {
    return clubRepository.getByName(name);
  }

  public Optional<Club> getById(final Long groupId) {
    return clubRepository.getById(groupId);
  }

  public List<Club> getGroupList() {
    return clubRepository.getGroupList();
  }
}
