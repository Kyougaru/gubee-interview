package br.com.gubee.interview.port.in.hero;

import br.com.gubee.interview.model.Hero;

import java.util.UUID;

public interface UpdateHeroUseCase {
    void update(Hero updatedHero, UUID id);
}
