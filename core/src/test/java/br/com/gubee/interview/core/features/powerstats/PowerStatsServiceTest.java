package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.entities.PowerStatsEntity;
import br.com.gubee.interview.core.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.core.stubs.PowerStatsRepositoryStub;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

public class PowerStatsServiceTest {
    private final PowerStatsService service = new PowerStatsService();
    private final PowerStatsRepositoryStub repositoryStub = new PowerStatsRepositoryStub();
    private PowerStatsEntity sampleEntity;

    @BeforeEach
    void init() {
        service.repository = repositoryStub;

        sampleEntity = new PowerStatsEntity(null,
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

        assertEquals(service.findById(inserted.getId()).getStrength(), (short) 20);
    }

    @Test
    void delete() {
        var inserted = service.insert(sampleEntity);
        service.delete(inserted.getId());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(inserted.getId()));
    }
}
