package br.com.gubee.interview.core.adapter.out.persistence;

import br.com.gubee.interview.core.adapter.out.persistence.entities.HeroEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface HeroRepository extends CrudRepository<HeroEntity, UUID> {
    List<HeroEntity> findByNameContainingIgnoreCase(String name);
}
