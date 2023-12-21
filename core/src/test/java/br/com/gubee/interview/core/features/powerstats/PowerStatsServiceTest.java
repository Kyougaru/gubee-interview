package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.entities.PowerStatsEntity;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
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
        var powerId = UUID.randomUUID();
        sampleEntity.setId(powerId);
        repositoryStub.returnedPower = Optional.of(sampleEntity);

        var result = service.findById(powerId);

        assertEquals(powerId, result.getId());
    }

    @Test
    void insert() {
        var result = service.insert(sampleEntity);

        assertEquals(sampleEntity.getId(), result.getId());
    }

    @Test
    void update() {
        var powerStats = new PowerStats(null,
                (short) 20,
                (short) 15,
                (short) 20,
                (short) 30,
                null,
                null);

        service.update(sampleEntity, powerStats);

        assertEquals(repositoryStub.returnedPower.get().getStrength(), (short) 20);
    }

    @Test
    void delete() {
        var powerId = UUID.randomUUID();
        service.delete(powerId);

        assertEquals(1, repositoryStub.deleteByIdInvocation);
    }
}

class PowerStatsRepositoryStub implements PowerStatsRepository {
    int deleteByIdInvocation = 0;

    Optional<PowerStatsEntity> returnedPower = Optional.empty();

    @Override
    public <S extends PowerStatsEntity> S save(S entity) {
        returnedPower = Optional.of(entity);
        return (S) returnedPower.get();
    }

    @Override
    public <S extends PowerStatsEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PowerStatsEntity> findById(UUID uuid) {
        return returnedPower;
    }

    @Override
    public boolean existsById(UUID uuid) {
        deleteByIdInvocation++;
        return true;
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
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

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