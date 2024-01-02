package repositories;

import entities.HeroEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface HeroRepository extends CrudRepository<HeroEntity, UUID> {
    List<HeroEntity> findByNameContainingIgnoreCase(String name);
}
