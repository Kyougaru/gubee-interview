package web;

import br.com.gubee.interview.core.port.in.hero.ManageHeroUseCase;
import dtos.HeroComparisonDTO;
import mapper.PowerStatsDifferenceDTOMapper;
import br.com.gubee.interview.model.Hero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/heroes")
@RequiredArgsConstructor
public class HeroController {
    private final ManageHeroUseCase manageHeroUseCase;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody Hero obj) {
        obj = manageHeroUseCase.insert(obj);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hero> findById(@PathVariable UUID id) {
        var hero = manageHeroUseCase.findById(id);
        return ResponseEntity.ok().body(hero);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Hero>> findByName(@RequestParam(value = "name", defaultValue = "") String name) {
        var list = manageHeroUseCase.findByName(name);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Hero obj, @PathVariable UUID id) {
        manageHeroUseCase.update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        manageHeroUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/compare")
    public ResponseEntity<HeroComparisonDTO> compareHeroes(@RequestParam(value = "hero1Id", defaultValue = "") UUID hero1Id,
                                              @RequestParam(value = "hero2Id", defaultValue = "") UUID hero2Id) {
        var hero1 = manageHeroUseCase.findById(hero1Id);
        var hero2 = manageHeroUseCase.findById(hero2Id);

        var stats1DTO = PowerStatsDifferenceDTOMapper.toDTO(hero1.getPowerStats(), hero2.getPowerStats());
        var stats2DTO = PowerStatsDifferenceDTOMapper.toDTO(hero2.getPowerStats(), hero1.getPowerStats());

        var result = new HeroComparisonDTO(hero1Id, hero2Id, stats1DTO, stats2DTO);
        return ResponseEntity.ok().body(result);
    }
}
