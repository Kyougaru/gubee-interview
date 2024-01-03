package br.com.gubee.interview.persistence.adapter;

import br.com.gubee.interview.common.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.persistence.repository.PowerStatsRepository;
import br.com.gubee.interview.port.out.powerstats.*;
import br.com.gubee.interview.persistence.mapper.PowerStatsMapper;
import br.com.gubee.interview.model.PowerStats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class PowerStatsPersistenceAdapter implements LoadPowerStatsPort, CreatePowerStatsPort, UpdatePowerStatsPort, DeletePowerStatsPort {
    private final PowerStatsRepository repository;

    @Override
    public PowerStats insert(PowerStats powerStats) {
        var savedEntity = repository.save(PowerStatsMapper.objToEntity(powerStats));
        return PowerStatsMapper.entityToObj(savedEntity);
    }

    @Override
    public PowerStats findById(UUID id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        return PowerStatsMapper.entityToObj(entity);
    }

    @Override
    public void update(PowerStats updatedPowerStats, UUID id) {
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        entity.setStrength(updatedPowerStats.getStrength());
        entity.setAgility(updatedPowerStats.getAgility());
        entity.setDexterity(updatedPowerStats.getDexterity());
        entity.setIntelligence(updatedPowerStats.getIntelligence());

        repository.save(entity);
    }

    @Override
    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
