package br.com.gubee.interview.core.stubs;

import br.com.gubee.interview.core.adapter.out.persistence.entities.HeroEntity;
import br.com.gubee.interview.core.adapter.out.persistence.HeroRepository;

import java.util.*;

public class HeroRepositoryStub implements HeroRepository {
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
