package br.com.gubee.interview.core.domain.service;

import br.com.gubee.interview.core.port.in.CRUDHeroUseCase;
import br.com.gubee.interview.core.port.out.CreateHeroPort;
import br.com.gubee.interview.core.port.out.DeleteHeroPort;
import br.com.gubee.interview.core.port.out.LoadHeroPort;
import br.com.gubee.interview.core.port.out.UpdateHeroPort;
import br.com.gubee.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CRUDHeroService implements CRUDHeroUseCase {
    private final LoadHeroPort loadHeroPort;
    private final CreateHeroPort createHeroPort;
    private final UpdateHeroPort updateHeroPort;
    private final DeleteHeroPort deleteHeroPort;

    @Override
    public Hero findById(UUID id) {
        return loadHeroPort.findById(id);
    }

    @Override
    public Hero insert(Hero hero) {
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
        deleteHeroPort.deleteById(id);
    }
}
