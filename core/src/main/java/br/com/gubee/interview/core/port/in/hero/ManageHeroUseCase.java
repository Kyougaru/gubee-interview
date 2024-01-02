package br.com.gubee.interview.core.port.in.hero;

import br.com.gubee.interview.model.Hero;

import java.util.List;
import java.util.UUID;

public interface ManageHeroUseCase {
    Hero findById(UUID id);
    Hero insert(Hero hero);
    List<Hero> findByName(String name);
    void update(Hero updatedHero, UUID id);
    void delete(UUID id);
}
