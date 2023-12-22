package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.dtos.HeroComparisonDTO;
import br.com.gubee.interview.core.mappers.PowerStatsMapper;
import br.com.gubee.interview.core.stubs.HeroServiceStub;
import br.com.gubee.interview.core.stubs.PowerStatsServiceStub;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class HeroControllerTest {
    private final HeroController controller = new HeroController();
    private final HeroServiceStub heroService = new HeroServiceStub();
    private final PowerStatsServiceStub powerStatsService = new PowerStatsServiceStub();
    private PowerStats samplePowerStats;
    private Hero sampleHero;

    @BeforeEach
    void init() {
        //Necessário mockar servlet request devido ao uri builder
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        controller.heroService = heroService;
        controller.powerStatsService = powerStatsService;

        samplePowerStats = new PowerStats(null,
                (short) 10,
                (short) 15,
                (short) 20,
                (short) 30,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()));

        sampleHero = new Hero(null,
                "Sample Hero",
                Hero.Race.HUMAN,
                samplePowerStats,
                true,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()));
    }

    @Test
    void insert() {
        var response = controller.insert(sampleHero);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().getLocation().getPath());
    }

    @Test
    void findById() {
        var insertedResponse = controller.insert(sampleHero);
        var insertedUUID = UUID.fromString(insertedResponse.getHeaders().getLocation().getPath().substring(1));

        var response = controller.findById(insertedUUID);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(insertedUUID, response.getBody().getId());
    }

    @Test
    void findByName() {
        controller.insert(sampleHero);
        var name = "Sample";

        var response = controller.findByName(name);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().get(0).getName().contains(name));
    }

    @Test
    void update() {
        var insertedResponse = controller.insert(sampleHero);
        var insertedUUID = UUID.fromString(insertedResponse.getHeaders().getLocation().getPath().substring(1));

        var hero = new Hero(null,
                "Super Man",
                Hero.Race.ALIEN,
                samplePowerStats,
                true,
                null,
                null);

        var response = controller.update(hero, insertedUUID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void delete() {
        var insertedResponse = controller.insert(sampleHero);
        var insertedUUID = UUID.fromString(insertedResponse.getHeaders().getLocation().getPath().substring(1));

        var response = controller.delete(insertedUUID);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void compareHeroes() {
        var insertedResponse1 = controller.insert(sampleHero);
        var insertedUUID1 = UUID.fromString(insertedResponse1.getHeaders().getLocation().getPath().substring(1));

        var powerStats = new PowerStats(null,
                (short) 10,
                (short) 15,
                (short) 20,
                (short) 30,
                Timestamp.from(Instant.now()),
                Timestamp.from(Instant.now()));

        var hero = new Hero(null,
                "Sample 2",
                Hero.Race.ALIEN,
                powerStats,
                true,
                null,
                null);

        var insertedResponse2 = controller.insert(hero);
        var insertedUUID2 = UUID.fromString(insertedResponse2.getHeaders().getLocation().getPath().substring(1));

        var powerDiff1 = PowerStatsMapper.objToDifferenceDTO(samplePowerStats, powerStats);
        var powerDiff2 = PowerStatsMapper.objToDifferenceDTO(powerStats, samplePowerStats);
        var heroDiff = new HeroComparisonDTO(insertedUUID1, insertedUUID2, powerDiff1, powerDiff2);

        var response = controller.compareHeroes(insertedUUID1, insertedUUID2);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertThat(heroDiff).usingRecursiveComparison().isEqualTo(response.getBody());
    }
}
