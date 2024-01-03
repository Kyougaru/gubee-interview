package br.com.gubee.interview.core.adapter.out.persistence.mapper;

import br.com.gubee.interview.core.adapter.out.persistence.entities.PowerStatsEntity;
import br.com.gubee.interview.model.PowerStats;

public class PowerStatsMapper {

    public static PowerStats entityToObj(PowerStatsEntity entity) {
        return new PowerStats(entity.getId(), entity.getStrength(), entity.getAgility(), entity.getDexterity(), entity.getIntelligence(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static PowerStatsEntity objToEntity(PowerStats stats) {
        return new PowerStatsEntity(stats.getId(), stats.getStrength(), stats.getAgility(), stats.getDexterity(), stats.getIntelligence(), stats.getCreatedAt(), stats.getUpdatedAt());
    }
}
