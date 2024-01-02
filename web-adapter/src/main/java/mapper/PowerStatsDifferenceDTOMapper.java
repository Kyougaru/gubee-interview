package mapper;

import dtos.PowerStatsDifferenceDTO;
import br.com.gubee.interview.model.PowerStats;

public class PowerStatsDifferenceDTOMapper {
    public static PowerStatsDifferenceDTO toDTO(PowerStats stats1, PowerStats stats2) {
        return new PowerStatsDifferenceDTO(stats1.getStrength() - stats2.getStrength(),
                stats1.getAgility() - stats2.getAgility(),
                stats1.getDexterity() - stats2.getDexterity(),
                stats1.getIntelligence() - stats2.getIntelligence());
    }
}
