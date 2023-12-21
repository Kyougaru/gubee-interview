package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.entities.HeroEntity;
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

        sampleEntity = new HeroEntity(UUID.randomUUID(),
                "Sample Hero",
                "HUMAN",
                UUID.randomUUID(),
                true,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()));
    }

    @Test
    void findById() {
        var heroId = UUID.randomUUID();
        sampleEntity.setId(heroId);
        repositoryStub.returnedHero = Optional.of(sampleEntity);

        var result = service.findById(heroId);

        assertEquals(heroId, result.getId());
    }

    @Test
    void insert() {
        var result = service.insert(sampleEntity);

        assertEquals(sampleEntity.getId(), result.getId());
        assertEquals(sampleEntity.getName(), result.getName());
    }

    @Test
    void findByName() {
        var name = "Sample";
        repositoryStub.returnedList = new ArrayList<>();
        repositoryStub.returnedList.add(sampleEntity);

        List<HeroEntity> result = service.findByName(name);

        assertTrue(result.get(0).getName().contains(name));
    }

    @Test
    void update() {
        var hero = new Hero(null,
                "Super Man",
                Hero.Race.ALIEN,
                null,
                true,
                null,
                null);

        service.update(sampleEntity, hero);

        assertEquals(repositoryStub.returnedHero.get().getName(), "Super Man");
        assertEquals(repositoryStub.returnedHero.get().getRace(), "ALIEN");
    }

    @Test
    void delete() {
        var heroId = UUID.randomUUID();
        service.delete(heroId);

        assertEquals(1, repositoryStub.deleteByIdInvocation);
    }
}

class HeroRepositoryStub implements HeroRepository {
    int deleteByIdInvocation = 0;

    Optional<HeroEntity> returnedHero = Optional.empty();
    List<HeroEntity> returnedList = Collections.emptyList();

    @Override
    public List<HeroEntity> findByNameContainingIgnoreCase(String name) {
        return returnedList;
    }

    @Override
    public <S extends HeroEntity> S save(S entity) {
        returnedHero = Optional.of(entity);
        return (S) returnedHero.get();
    }

    @Override
    public <S extends HeroEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<HeroEntity> findById(UUID uuid) {
        return returnedHero;
    }

    @Override
    public boolean existsById(UUID uuid) {
        deleteByIdInvocation++;
        return true;
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
        return 0;
    }

    @Override
    public void deleteById(UUID uuid) {

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