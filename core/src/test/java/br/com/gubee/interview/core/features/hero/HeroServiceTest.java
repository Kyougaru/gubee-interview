package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.entities.HeroEntity;
import br.com.gubee.interview.core.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.model.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HeroServiceTest {
    private final HeroService service = new HeroService();
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

        assertEquals(repositoryStub.heroes.get(inserted.getId()).getName(), "Super Man");
        assertEquals(repositoryStub.heroes.get(inserted.getId()).getRace(), "ALIEN");
    }

    @Test
    void delete() {
        var inserted = service.insert(sampleEntity);
        service.delete(inserted.getId());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(inserted.getId()));
    }
}

class HeroRepositoryStub implements HeroRepository {
    HashMap<UUID, HeroEntity> heroes = new HashMap<>();

    @Override
    public List<HeroEntity> findByNameContainingIgnoreCase(String name) {
        List<HeroEntity> matches = new ArrayList<>();
        name = name.toLowerCase();

        for (HeroEntity hero : heroes.values()) {
            if (hero.getName().toLowerCase().contains(name))
                matches.add(hero);
        }

        return matches;
    }

    @Override
    public <S extends HeroEntity> S save(S entity) {
        var generatedId = UUID.randomUUID();
        heroes.put(generatedId, entity);
        entity.setId(generatedId);
        return entity;
    }

    @Override
    public <S extends HeroEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<HeroEntity> findById(UUID uuid) {
        if (heroes.containsKey(uuid)) {
            return Optional.of(heroes.get(uuid));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID uuid) {
        return heroes.containsKey(uuid);
    }

    @Override
    public Iterable<HeroEntity> findAll() {
        return null;
    }

    @Override
    public Iterable<HeroEntity> findAllById(Iterable<UUID> uuids) {
        return null;
    }

    @Override
    public long count() {
        return heroes.size();
    }

    @Override
    public void deleteById(UUID uuid) {
        heroes.remove(uuid);
    }

    @Override
    public void delete(HeroEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {

    }

    @Override
    public void deleteAll(Iterable<? extends HeroEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }
}