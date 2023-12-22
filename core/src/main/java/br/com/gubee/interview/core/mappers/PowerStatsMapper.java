package br.com.gubee.interview.core.mappers;

import br.com.gubee.interview.core.dtos.PowerStatsDifferenceDTO;
import br.com.gubee.interview.core.entities.PowerStatsEntity;
import br.com.gubee.interview.model.PowerStats;

public class PowerStatsMapper {

    public static PowerStats entityToObj(PowerStatsEntity entity) {
        return new PowerStats(entity.getId(), entity.getStrength(), entity.getAgility(), entity.getDexterity(), entity.getIntelligence(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static PowerStatsEntity objToEntity(PowerStats stats) {
        return new PowerStatsEntity(stats.getId(), stats.getStrength(), stats.getAgility(), stats.getDexterity(), stats.getIntelligence(), stats.getCreatedAt(), stats.getUpdatedAt());
    }

    public static PowerStatsDifferenceDTO entityToDifferenceDTO(PowerStatsEntity stats1, PowerStatsEntity stats2) {
        return new PowerStatsDifferenceDTO(stats1.getStrength() - stats2.getStrength(), stats1.getAgility() - stats2.getAgility(), stats1.getDexterity() - stats2.getDexterity(), stats1.getIntelligence() - stats2.getIntelligence());
    }

    public static PowerStatsDifferenceDTO objToDifferenceDTO(PowerStats stats1, PowerStats stats2) {
        return new PowerStatsDifferenceDTO(stats1.getStrength() - stats2.getStrength(), stats1.getAgility() - stats2.getAgility(), stats1.getDexterity() - stats2.getDexterity(), stats1.getIntelligence() - stats2.getIntelligence());
    }
}
