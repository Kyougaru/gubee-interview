package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.entities.PowerStatsEntity;
import br.com.gubee.interview.core.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class PowerStatsService {
    @Autowired
    private PowerStatsRepository repository;

    public PowerStatsEntity findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public PowerStatsEntity insert(PowerStatsEntity entity) {
        entity.setCreatedAt(Timestamp.from(Instant.now()));
        entity.setUpdatedAt(Timestamp.from(Instant.now()));
        return repository.save(entity);
    }

    public void update(PowerStatsEntity entity, PowerStats obj) {
        entity.setStrength(obj.getStrength());
        entity.setAgility(obj.getAgility());
        entity.setDexterity(obj.getDexterity());
        entity.setIntelligence(obj.getIntelligence());
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
