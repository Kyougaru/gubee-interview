package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.entities.HeroEntity;
import br.com.gubee.interview.core.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class HeroService {
    @Autowired
    HeroRepository repository;

    public HeroEntity findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public HeroEntity insert(HeroEntity entity) {
        entity.setCreatedAt(Timestamp.from(Instant.now()));
        entity.setUpdatedAt(Timestamp.from(Instant.now()));
        return repository.save(entity);
    }

    public List<HeroEntity> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    public void update(HeroEntity entity, Hero obj) {
        entity.setName(obj.getName());
        entity.setRace(obj.getRace().name());
        entity.setEnabled(obj.getEnabled());
        entity.setUpdatedAt(Timestamp.from(Instant.now()));
        repository.save(entity);
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
