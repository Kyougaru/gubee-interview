package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.dtos.HeroComparisonDTO;
import br.com.gubee.interview.core.dtos.PowerStatsDifferenceDTO;
import br.com.gubee.interview.core.entities.HeroEntity;
import br.com.gubee.interview.core.entities.PowerStatsEntity;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.core.mappers.PowerStatsMapper;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
public class HeroControllerTest {
    @Mock
    private HeroService heroService;

    @Mock
    private PowerStatsService powerStatsService;

    @InjectMocks
    private HeroController controller;

    @Test
    void testInsert() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Timestamp random = Timestamp.from(Instant.now());

        PowerStats statsToInsert = new PowerStats(null, (short) 10, (short) 15, (short) 20, (short) 30, random, random);
        Hero heroToInsert = new Hero(null, "Bruce", Hero.Race.HUMAN, statsToInsert, true, random, random);

        UUID powerStatsId = UUID.randomUUID();
        UUID heroId = UUID.randomUUID();

        PowerStatsEntity toInsertStats = new PowerStatsEntity(null, (short) 10, (short) 15, (short) 20, (short) 30, random, random);
        HeroEntity toInsertHero = new HeroEntity(null, "Bruce", "HUMAN", powerStatsId, true, random, random);

        PowerStatsEntity insertedStats = new PowerStatsEntity(powerStatsId, (short) 10, (short) 15, (short) 20, (short) 30, random, random);
        HeroEntity insertedHero = new HeroEntity(heroId, "Bruce", "HUMAN", powerStatsId, true, random, random);

        BDDMockito.when(powerStatsService.insert(Mockito.any(PowerStatsEntity.class))).thenReturn(insertedStats);
        BDDMockito.when(heroService.insert(Mockito.any(HeroEntity.class))).thenReturn(insertedHero);
        ResponseEntity<Void> response = controller.insert(heroToInsert);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals("/" + heroId, response.getHeaders().getLocation().getPath());
        BDDMockito.verify(powerStatsService, Mockito.times(1)).insert(toInsertStats);
        BDDMockito.verify(heroService, Mockito.times(1)).insert(toInsertHero);
    }

    @Test
    void testFindById() {
        UUID powerStatsId = UUID.randomUUID();
        UUID heroId = UUID.randomUUID();
        PowerStatsEntity mockStats = new PowerStatsEntity(powerStatsId, (short) 10, (short) 15, (short) 20, (short) 30, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        HeroEntity mockHero = new HeroEntity(heroId, "Bruce", "HUMAN", powerStatsId, true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        BDDMockito.when(powerStatsService.findById(powerStatsId)).thenReturn(mockStats);
        BDDMockito.when(heroService.findById(heroId)).thenReturn(mockHero);
        ResponseEntity<Hero> response = controller.findById(heroId);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(heroId, response.getBody().getId());
        BDDMockito.verify(powerStatsService, Mockito.times(1)).findById(powerStatsId);
        BDDMockito.verify(heroService, Mockito.times(1)).findById(heroId);
    }

    @Test
    void testFindByName() {
        String name = "Bruce";
        UUID powerStatsId = UUID.randomUUID();
        UUID heroId = UUID.randomUUID();
        PowerStatsEntity mockStats = new PowerStatsEntity(powerStatsId, (short) 10, (short) 15, (short) 20, (short) 30, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        HeroEntity mockHero = new HeroEntity(heroId, "Bruce", "HUMAN", powerStatsId, true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        List<HeroEntity> mockList = new ArrayList<>();
        mockList.add(mockHero);

        BDDMockito.when(heroService.findByName(name)).thenReturn(mockList);
        BDDMockito.when(powerStatsService.findById(powerStatsId)).thenReturn(mockStats);

        ResponseEntity<List<Hero>> response = controller.findByName(name);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(name, response.getBody().get(0).getName());
        BDDMockito.verify(heroService, Mockito.times(1)).findByName(name);
        BDDMockito.verify(powerStatsService, Mockito.times(1)).findById(powerStatsId);
    }

    @Test
    void testUpdateHero() {
        Timestamp random = Timestamp.from(Instant.now());
        PowerStats statsToInsert = new PowerStats(null, (short) 99, (short) 99, (short) 99, (short) 20, random, random);
        Hero heroToInsert = new Hero(null, "Super Man", Hero.Race.ALIEN, statsToInsert, true, random, random);

        UUID powerStatsId = UUID.randomUUID();
        UUID heroId = UUID.randomUUID();

        PowerStatsEntity beforeUpdateStats = new PowerStatsEntity(powerStatsId, (short) 10, (short) 15, (short) 20, (short) 30, random, random);
        HeroEntity beforeUpdateHero = new HeroEntity(heroId, "Bruce", "HUMAN", powerStatsId, true, random, random);

        BDDMockito.when(heroService.findById(heroId)).thenReturn(beforeUpdateHero);
        BDDMockito.when(powerStatsService.findById(powerStatsId)).thenReturn(beforeUpdateStats);

        ResponseEntity<Void> response = controller.update(heroToInsert, heroId);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        BDDMockito.verify(heroService, Mockito.times(1)).findById(heroId);
        BDDMockito.verify(powerStatsService, Mockito.times(1)).findById(powerStatsId);
        BDDMockito.verify(powerStatsService, Mockito.times(1)).update(beforeUpdateStats, statsToInsert);
        BDDMockito.verify(heroService, Mockito.times(1)).update(beforeUpdateHero, heroToInsert);
    }

    @Test
    void testDeleteHero() {
        UUID heroId = UUID.randomUUID();
        UUID powerStatsId = UUID.randomUUID();
        HeroEntity mockHero = new HeroEntity(heroId, "Bruce", "HUMAN", powerStatsId, true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        BDDMockito.when(heroService.findById(heroId)).thenReturn(mockHero);
        ResponseEntity<Void> response = controller.delete(heroId);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        BDDMockito.verify(heroService, Mockito.times(1)).findById(heroId);
        BDDMockito.verify(heroService, Mockito.times(1)).delete(heroId);
        BDDMockito.verify(powerStatsService, Mockito.times(1)).delete(powerStatsId);
    }

    @Test
    void testCompareHeroes() {
        UUID hero1Id = UUID.randomUUID();
        UUID powerStats1Id = UUID.randomUUID();
        UUID hero2Id = UUID.randomUUID();
        UUID powerStats2Id = UUID.randomUUID();

        PowerStatsEntity powerStats1 = new PowerStatsEntity(powerStats1Id, (short) 10, (short) 15, (short) 20, (short) 30, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        HeroEntity hero1 = new HeroEntity(hero1Id, "Bruce", "HUMAN", powerStats1Id, true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        PowerStatsEntity powerStats2 = new PowerStatsEntity(powerStats2Id, (short) 99, (short) 99, (short) 99, (short) 20, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        HeroEntity hero2 = new HeroEntity(hero2Id, "Super Man", "ALIEN", powerStats2Id, true, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        PowerStatsDifferenceDTO stats1DTO = PowerStatsMapper.entityToDifferenceDTO(powerStats1, powerStats2);
        PowerStatsDifferenceDTO stats2DTO = PowerStatsMapper.entityToDifferenceDTO(powerStats2, powerStats1);

        HeroComparisonDTO comparisonDTO = new HeroComparisonDTO(hero1Id, hero2Id, stats1DTO, stats2DTO);

        BDDMockito.when(heroService.findById(hero1Id)).thenReturn(hero1);
        BDDMockito.when(heroService.findById(hero2Id)).thenReturn(hero2);
        BDDMockito.when(powerStatsService.findById(powerStats1Id)).thenReturn(powerStats1);
        BDDMockito.when(powerStatsService.findById(powerStats2Id)).thenReturn(powerStats2);

        ResponseEntity<HeroComparisonDTO> response = controller.compareHeroes(hero1Id, hero2Id);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        org.assertj.core.api.Assertions.assertThat(comparisonDTO).usingRecursiveComparison().isEqualTo(response.getBody());
        BDDMockito.verify(heroService, Mockito.times(1)).findById(hero1Id);
        BDDMockito.verify(heroService, Mockito.times(1)).findById(hero2Id);
        BDDMockito.verify(powerStatsService, Mockito.times(1)).findById(powerStats1Id);
        BDDMockito.verify(powerStatsService, Mockito.times(1)).findById(powerStats2Id);
    }
}
