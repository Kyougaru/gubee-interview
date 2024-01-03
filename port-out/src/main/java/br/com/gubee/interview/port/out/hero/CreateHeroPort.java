package br.com.gubee.interview.port.out.hero;

import br.com.gubee.interview.model.Hero;

public interface CreateHeroPort {
    Hero insert(Hero hero);
}
