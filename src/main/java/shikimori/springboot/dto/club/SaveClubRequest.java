package shikimori.springboot.dto.club;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
public class SaveClubRequest {
  String name;
}
