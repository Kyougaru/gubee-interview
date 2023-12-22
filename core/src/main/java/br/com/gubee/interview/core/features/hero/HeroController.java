package br.com.gubee.interview.core.features.hero;

import br.com.gubee.interview.core.dtos.HeroComparisonDTO;
import br.com.gubee.interview.core.dtos.PowerStatsDifferenceDTO;
import br.com.gubee.interview.core.entities.HeroEntity;
import br.com.gubee.interview.core.entities.PowerStatsEntity;
import br.com.gubee.interview.core.features.powerstats.PowerStatsService;
import br.com.gubee.interview.core.mappers.HeroMapper;
import br.com.gubee.interview.core.mappers.PowerStatsMapper;
import br.com.gubee.interview.core.util.URL;
import br.com.gubee.interview.model.Hero;
import br.com.gubee.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/heroes")
public class HeroController {
    @Autowired
    HeroService heroService;

    @Autowired
    PowerStatsService powerStatsService;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Hero obj) {
        PowerStatsEntity statsEntity = PowerStatsMapper.objToEntity(obj.getPowerStats());
        statsEntity = powerStatsService.insert(statsEntity);

        HeroEntity heroEntity = HeroMapper.objToEntity(obj, powerStatsService.insert(statsEntity).getId());
        heroEntity = heroService.insert(heroEntity);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(heroEntity.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hero> findById(@PathVariable UUID id) {
        HeroEntity heroEntity = heroService.findById(id);
        PowerStatsEntity statsEntity = powerStatsService.findById(heroEntity.getPowerStatsId());

        PowerStats stats = PowerStatsMapper.entityToObj(statsEntity);
        Hero hero = HeroMapper.entityToObj(heroEntity, stats);

        return ResponseEntity.ok().body(hero);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Hero>> findByName(@RequestParam(value = "name", defaultValue = "") String name) {
        List<HeroEntity> entitiesList = heroService.findByName(URL.decodeParam(name));
        List<Hero> list = new ArrayList<>();

        for(HeroEntity heroEntity : entitiesList) {
            PowerStatsEntity statsEntity = powerStatsService.findById(heroEntity.getPowerStatsId());

            PowerStats stats = PowerStatsMapper.entityToObj(statsEntity);
            Hero hero = HeroMapper.entityToObj(heroEntity, stats);

            list.add(hero);
        }

        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Hero obj, @PathVariable UUID id) {
        HeroEntity heroEntity = heroService.findById(id);
        PowerStatsEntity statsEntity = powerStatsService.findById(heroEntity.getPowerStatsId());

        powerStatsService.update(statsEntity, obj.getPowerStats());
        heroService.update(heroEntity, obj);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        HeroEntity heroEntity = heroService.findById(id);
        UUID statsId = heroEntity.getPowerStatsId();

        heroService.delete(id);
        powerStatsService.delete(statsId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/compare")
    public ResponseEntity<HeroComparisonDTO> compareHeroes(@RequestParam(value = "hero1Id", defaultValue = "") UUID hero1Id,
                                              @RequestParam(value = "hero2Id", defaultValue = "") UUID hero2Id) {
        HeroEntity hero1Entity = heroService.findById(hero1Id);
        HeroEntity hero2Entity = heroService.findById(hero2Id);

        PowerStatsEntity stats1Entity = powerStatsService.findById(hero1Entity.getPowerStatsId());
        PowerStatsEntity stats2Entity = powerStatsService.findById(hero2Entity.getPowerStatsId());

        PowerStatsDifferenceDTO stats1DTO = PowerStatsMapper.entityToDifferenceDTO(stats1Entity, stats2Entity);
        PowerStatsDifferenceDTO stats2DTO = PowerStatsMapper.entityToDifferenceDTO(stats2Entity, stats1Entity);

        HeroComparisonDTO result = new HeroComparisonDTO(hero1Id, hero2Id, stats1DTO, stats2DTO);

        return ResponseEntity.ok().body(result);
    }
}
