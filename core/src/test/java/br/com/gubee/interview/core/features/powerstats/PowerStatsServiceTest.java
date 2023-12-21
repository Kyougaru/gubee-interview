package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.entities.PowerStatsEntity;
import br.com.gubee.interview.core.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PowerStatsServiceTest {
    private final PowerStatsService service = new PowerStatsService();
    private final PowerStatsRepositoryStub repositoryStub = new PowerStatsRepositoryStub();
    private PowerStatsEntity sampleEntity;

    @BeforeEach
    void init() {
        service.repository = repositoryStub;

        sampleEntity = new PowerStatsEntity(UUID.randomUUID(),
                (short) 10,
                (short) 15,
                (short) 20,
                (short) 30,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()));
    }

    @Test
    void findById() {
        var inserted = service.insert(sampleEntity);
        var result = service.findById(inserted.getId());

        assertEquals(inserted.getId(), result.getId());
        assertNotNull(result.getId());
    }

    @Test
    void insert() {
        var result = service.insert(sampleEntity);

        assertNotNull(result.getId());
    }

    @Test
    void update() {
        var inserted = service.insert(sampleEntity);

        var powerStats = new PowerStats(null,
                (short) 20,
                (short) 15,
                (short) 20,
                (short) 30,
                null,
                null);

        service.update(inserted, powerStats);

        assertEquals(repositoryStub.powerStats.get(inserted.getId()).getStrength(), (short) 20);
    }

    @Test
    void delete() {
        var inserted = service.insert(sampleEntity);
        service.delete(inserted.getId());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(inserted.getId()));
    }
}

class PowerStatsRepositoryStub implements PowerStatsRepository {
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