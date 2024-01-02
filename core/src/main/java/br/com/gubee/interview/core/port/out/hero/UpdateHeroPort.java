package br.com.gubee.interview.core.port.out.hero;

import br.com.gubee.interview.model.Hero;

import java.util.UUID;

public interface UpdateHeroPort {
    void update(Hero updatedHero, UUID id);
}
