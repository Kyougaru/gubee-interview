package br.com.gubee.interview.core.stubs;

import br.com.gubee.interview.core.adapter.out.persistence.entities.PowerStatsEntity;
import br.com.gubee.interview.core.adapter.out.persistence.PowerStatsRepository;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class PowerStatsRepositoryStub implements PowerStatsRepository {
    HashMap<UUID, PowerStatsEntity> powerStats = new HashMap<>();

    @Override
    public <S extends PowerStatsEntity> S save(S entity) {
        var generatedId = UUID.randomUUID();
        powerStats.put(generatedId, entity);
        entity.setId(generatedId);
        return entity;
    }

    @Override
    public <S extends PowerStatsEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PowerStatsEntity> findById(UUID uuid) {
        if (powerStats.containsKey(uuid)) {
            return Optional.of(powerStats.get(uuid));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return powerStats.containsKey(uuid);
    }

    @Override
    public Iterable<PowerStatsEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<PowerStatsEntity> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return powerStats.size();
    }

    @Override
    public void deleteById(UUID uuid) {
        powerStats.remove(uuid);
    }

    @Override
    public void delete(PowerStatsEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends PowerStatsEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
