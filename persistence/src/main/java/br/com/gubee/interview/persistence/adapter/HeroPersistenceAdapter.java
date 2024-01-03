package br.com.gubee.interview.persistence.adapter;

import br.com.gubee.interview.common.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.persistence.repository.HeroRepository;
import br.com.gubee.interview.persistence.repository.PowerStatsRepository;
import br.com.gubee.interview.port.out.hero.*;
import br.com.gubee.interview.persistence.mapper.HeroMapper;
import br.com.gubee.interview.persistence.mapper.PowerStatsMapper;
import br.com.gubee.interview.persistence.entities.HeroEntity;
import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class HeroPersistenceAdapter implements LoadHeroPort, CreateHeroPort, UpdateHeroPort, DeleteHeroPort {
    private final HeroRepository heroRepository;
    private final PowerStatsRepository powerStatsRepository;

    @Override
    public Hero insert(Hero hero) {
        var savedEntity = heroRepository.save(HeroMapper.objToEntity(hero, hero.getPowerStats().getId()));
        return HeroMapper.entityToObj(savedEntity, hero.getPowerStats());
    }

    @Override
    public Hero findById(UUID id) {
        var heroEntity = heroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        var powerStatsEntity = powerStatsRepository.findById(heroEntity.getPowerStatsId()).get();

        var powerStats = PowerStatsMapper.entityToObj(powerStatsEntity);
        return HeroMapper.entityToObj(heroEntity, powerStats);
    }

    @Override
    public List<Hero> findByName(String name) {
        List<Hero> list = new ArrayList<>();

        var heroEntityList = heroRepository.findByNameContainingIgnoreCase(name);

        for (HeroEntity heroEntity : heroEntityList) {
            var powerStatsEntity = powerStatsRepository.findById(heroEntity.getPowerStatsId()).get();

            var powerStats = PowerStatsMapper.entityToObj(powerStatsEntity);
            Hero hero = HeroMapper.entityToObj(heroEntity, powerStats);

            list.add(hero);
        }

        return list;
    }

    @Override
    public void update(Hero updatedHero, UUID id) {
        var heroEntity = heroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        heroEntity.setName(updatedHero.getName());
        heroEntity.setRace(updatedHero.getRace().name());
        heroEntity.setEnabled(updatedHero.getEnabled());

        heroRepository.save(heroEntity);
    }

    @Override
    public void deleteById(UUID id) {
        if (!heroRepository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        heroRepository.deleteById(id);
    }
}
