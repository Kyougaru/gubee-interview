package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.domain.service.ManageHeroService;
import br.com.gubee.interview.core.adapter.out.persistence.entities.HeroEntity;
import br.com.gubee.interview.core.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.core.stubs.HeroRepositoryStub;
import br.com.gubee.interview.model.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class HeroServiceTest {
    /*private final ManageHeroService service = new ManageHeroService();
    private final HeroRepositoryStub repositoryStub = new HeroRepositoryStub();
    private HeroEntity sampleEntity;

    @BeforeEach
    void init() {
        service.repository = repositoryStub;

        sampleEntity = new HeroEntity(null,
                "Sample Hero",
                "HUMAN",
                UUID.randomUUID(),
                true,
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
        assertEquals(sampleEntity.getName(), result.getName());
    }

    @Test
    void findByName() {
        var name = "Sample";

        service.insert(sampleEntity);
        List<HeroEntity> result = service.findByName(name);

        assertTrue(result.get(0).getName().contains(name));
    }

    @Test
    void update() {
        var inserted = service.insert(sampleEntity);

        var hero = new Hero(null,
                "Super Man",
                Hero.Race.ALIEN,
                null,
                true,
                null,
                null);

        service.update(inserted, hero);

        assertEquals(service.findById(inserted.getId()).getName(), "Super Man");
        assertEquals(service.findById(inserted.getId()).getRace(), "ALIEN");
    }

    @Test
    void delete() {
        var inserted = service.insert(sampleEntity);
        service.delete(inserted.getId());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(inserted.getId()));
    }*/
}
