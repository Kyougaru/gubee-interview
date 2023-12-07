package br.com.gubee.interview.core.mappers;

import br.com.gubee.interview.core.entities.HeroEntity;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public class HeroMapper {
    public static Hero entityToObj(HeroEntity entity, PowerStats stats) {
        return new Hero(entity.getId(), entity.getName(), Hero.Race.valueOf(entity.getRace()), stats, entity.getEnabled(), entity.getCreatedAt(), entity.getUpdatedAt());
    }

    public static HeroEntity objToEntity(Hero hero, UUID statsId) {
        return new HeroEntity(hero.getId(), hero.getName(), hero.getRace().name(), statsId, hero.getEnabled(), hero.getCreatedAt(), hero.getUpdatedAt());
    }
}
