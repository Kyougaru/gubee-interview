package br.com.gubee.interview.core.port.in.hero;

import br.com.gubee.interview.model.Hero;

import java.util.UUID;

public interface FindByIdHeroUseCase {
    Hero findById(UUID id);
}