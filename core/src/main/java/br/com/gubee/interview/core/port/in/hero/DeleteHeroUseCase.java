package br.com.gubee.interview.core.port.in.hero;

import java.util.UUID;

public interface DeleteHeroUseCase {
    void delete(UUID id);
}
