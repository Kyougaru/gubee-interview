package br.com.gubee.interview.core.port.out.hero;

import java.util.UUID;

public interface DeleteHeroPort {
    void deleteById(UUID id);
}
