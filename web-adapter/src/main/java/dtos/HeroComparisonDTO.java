package dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class HeroComparisonDTO {
    private UUID hero1Id;
    private UUID hero2Id;
    private PowerStatsDifferenceDTO hero1Stats;
    private PowerStatsDifferenceDTO hero2Stats;
}
