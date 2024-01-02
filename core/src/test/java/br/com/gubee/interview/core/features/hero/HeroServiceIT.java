package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.domain.service.ManageHeroService;
import br.com.gubee.interview.core.adapter.out.persistence.entities.HeroEntity;
import br.com.gubee.interview.core.adapter.out.persistence.entities.PowerStatsEntity;
import br.com.gubee.interview.core.exceptions.ResourceNotFoundException;
import br.com.gubee.interview.core.domain.service.ManagePowerStatsService;
import br.com.gubee.interview.model.Hero;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@ActiveProfiles("it")
@SpringBootTest
@Transactional
public class HeroServiceIT {
    /*@Autowired
    private ManageHeroService heroService;

    @Autowired
    private ManagePowerStatsService powerStatsService;

    @Test
    void testInsert() {
        PowerStatsEntity stats = new PowerStatsEntity(null, (short) 10, (short) 15, (short) 20, (short) 30, null, null);;
        PowerStatsEntity insertedStats = powerStatsService.insert(stats);

        HeroEntity hero = new HeroEntity(null, "Bruce", "HUMAN", insertedStats.getId(), true, null, null);
        HeroEntity insertedHero = heroService.insert(hero);

        Assertions.assertNotNull(insertedHero.getId());
        Assertions.assertEquals(hero.getName(), insertedHero.getName());
    }

    @Test
    void testFindById() {
        PowerStatsEntity stats = new PowerStatsEntity(null, (short) 10, (short) 15, (short) 20, (short) 30, null, null);
        PowerStatsEntity insertedStats = powerStatsService.insert(stats);

        HeroEntity hero = new HeroEntity(null, "Bruce", "HUMAN", insertedStats.getId(), true, null, null);
        HeroEntity insertedHero = heroService.insert(hero);

        UUID heroId = insertedHero.getId();
        HeroEntity heroFound = heroService.findById(heroId);

        Assertions.assertEquals(heroId, heroFound.getId());
    }

    @Test
    void testFindByName() {
        PowerStatsEntity stats = new PowerStatsEntity(null, (short) 10, (short) 15, (short) 20, (short) 30, null, null);;
        PowerStatsEntity insertedStats = powerStatsService.insert(stats);

        HeroEntity hero = new HeroEntity(null, "Bruce", "HUMAN", insertedStats.getId(), true, null, null);
        heroService.insert(hero);

        String heroName = "bruce";
        List<HeroEntity> result = heroService.findByName(heroName);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertTrue(result.stream().allMatch(heroEntity -> hero.getName().toLowerCase().equals(heroName)));
    }

    @Test
    void testUpdate() {
        PowerStatsEntity stats = new PowerStatsEntity(null, (short) 10, (short) 15, (short) 20, (short) 30, null, null);;
        PowerStatsEntity insertedStats = powerStatsService.insert(stats);

        HeroEntity hero = new HeroEntity(null, "Bruce", "HUMAN", insertedStats.getId(), true, null, null);
        HeroEntity insertedHero = heroService.insert(hero);

        Hero heroRequestBody = new Hero(null, "Super Man", Hero.Race.ALIEN, null, false, null, null);

        heroService.update(insertedHero, heroRequestBody);
        HeroEntity updatedHero = heroService.findById(insertedHero.getId());

        Assertions.assertEquals(updatedHero.getId(), insertedHero.getId());
        Assertions.assertEquals(updatedHero.getName(), heroRequestBody.getName());
        Assertions.assertEquals(updatedHero.getRace(), heroRequestBody.getRace().name());
        Assertions.assertEquals(updatedHero.getEnabled(), heroRequestBody.getEnabled());
    }

    @Test
    void testDelete() {
        PowerStatsEntity stats = new PowerStatsEntity(null, (short) 10, (short) 15, (short) 20, (short) 30, null, null);;
        PowerStatsEntity insertedStats = powerStatsService.insert(stats);

        HeroEntity hero = new HeroEntity(null, "Bruce", "HUMAN", insertedStats.getId(), true, null, null);
        HeroEntity insertedHero = heroService.insert(hero);

        heroService.delete(insertedHero.getId());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> heroService.findById(insertedHero.getId()));
    }*/
}
