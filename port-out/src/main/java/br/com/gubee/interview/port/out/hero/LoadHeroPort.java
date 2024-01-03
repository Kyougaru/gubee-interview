package br.com.gubee.interview.port.out.hero;

import br.com.gubee.interview.model.Hero;

import java.util.List;
import java.util.UUID;

public interface LoadHeroPort {
    Hero findById(UUID id);
    List<Hero> findByName(String name);
}
