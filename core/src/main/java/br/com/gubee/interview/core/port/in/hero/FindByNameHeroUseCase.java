package br.com.gubee.interview.core.port.in.hero;

import br.com.gubee.interview.model.Hero;

import java.util.List;

public interface FindByNameHeroUseCase {
    List<Hero> findByName(String name);
}
