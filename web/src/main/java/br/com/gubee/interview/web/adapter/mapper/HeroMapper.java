package br.com.gubee.interview.web.adapter.mapper;

import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import br.com.gubee.interview.web.adapter.dtos.HeroDTO;

public class HeroMapper {
    public static Hero dtoToObj(HeroDTO heroDto) {
        var powerStatsDto = heroDto.getPowerStats();
        var powerStats = new PowerStats(null, powerStatsDto.getStrength(), powerStatsDto.getAgility(), powerStatsDto.getDexterity(), powerStatsDto.getIntelligence(), null, null);
        return new Hero(null, heroDto.getName(), Hero.Race.valueOf(heroDto.getRace()), powerStats, heroDto.getEnabled(), null, null);
    }
}
