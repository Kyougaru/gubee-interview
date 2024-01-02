package br.com.gubee.interview.core.domain.service;

import br.com.gubee.interview.core.port.in.hero.ManageHeroUseCase;
import br.com.gubee.interview.core.port.out.hero.CreateHeroPort;
import br.com.gubee.interview.core.port.out.hero.DeleteHeroPort;
import br.com.gubee.interview.core.port.out.hero.LoadHeroPort;
import br.com.gubee.interview.core.port.out.hero.UpdateHeroPort;
import br.com.gubee.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManageHeroService implements ManageHeroUseCase {
    private final LoadHeroPort loadHeroPort;
    private final CreateHeroPort createHeroPort;
    private final UpdateHeroPort updateHeroPort;
    private final DeleteHeroPort deleteHeroPort;
    private final ManagePowerStatsService managePowerStatsService;

    @Override
    public Hero findById(UUID id) {
        return loadHeroPort.findById(id);
    }

    @Override
    public Hero insert(Hero hero) {
        var powerStats = managePowerStatsService.insert(hero.getPowerStats());
        hero.getPowerStats().setId(powerStats.getId());

        var instant = Timestamp.from(Instant.now());
        hero.setCreatedAt(instant);
        hero.setUpdatedAt(instant);

        return createHeroPort.insert(hero);
    }

    @Override
    public List<Hero> findByName(String name) {
        return loadHeroPort.findByName(name);
    }

    @Override
    public void update(Hero updatedHero, UUID id) {
        var instant = Timestamp.from(Instant.now());
        updatedHero.setUpdatedAt(instant);

        updateHeroPort.update(updatedHero, id);
    }

    @Override
    public void delete(UUID id) {
        var hero = loadHeroPort.findById(id);
        deleteHeroPort.deleteById(id);
        managePowerStatsService.delete(hero.getPowerStats().getId());
    }
}
