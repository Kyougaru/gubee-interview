package br.com.gubee.interview.web.adapter;

import br.com.gubee.interview.port.in.hero.*;
import br.com.gubee.interview.web.adapter.dtos.HeroComparisonDTO;
import br.com.gubee.interview.web.adapter.dtos.HeroDTO;
import br.com.gubee.interview.web.adapter.mapper.HeroMapper;
import br.com.gubee.interview.web.adapter.mapper.PowerStatsDifferenceDTOMapper;
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
    private final FindByIdHeroUseCase findByIdHeroUseCase;
    private final InsertHeroUseCase insertHeroUseCase;
    private final FindByNameHeroUseCase findByNameHeroUseCase;
    private final UpdateHeroUseCase updateHeroUseCase;
    private final DeleteHeroUseCase deleteHeroUseCase;

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody HeroDTO objDto) {
        var obj = HeroMapper.dtoToObj(objDto);
        obj = insertHeroUseCase.insert(obj);
        var uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Hero> findById(@PathVariable UUID id) {
        var hero = findByIdHeroUseCase.findById(id);
        return ResponseEntity.ok().body(hero);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Hero>> findByName(@RequestParam(value = "name", defaultValue = "") String name) {
        var list = findByNameHeroUseCase.findByName(name);
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody HeroDTO objDto, @PathVariable UUID id) {
        var obj = HeroMapper.dtoToObj(objDto);
        updateHeroUseCase.update(obj, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteHeroUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/compare")
    public ResponseEntity<HeroComparisonDTO> compareHeroes(@RequestParam(value = "hero1Id", defaultValue = "") UUID hero1Id,
                                                           @RequestParam(value = "hero2Id", defaultValue = "") UUID hero2Id) {
        var hero1 = findByIdHeroUseCase.findById(hero1Id);
        var hero2 = findByIdHeroUseCase.findById(hero2Id);

        var stats1DTO = PowerStatsDifferenceDTOMapper.toDTO(hero1.getPowerStats(), hero2.getPowerStats());
        var stats2DTO = PowerStatsDifferenceDTOMapper.toDTO(hero2.getPowerStats(), hero1.getPowerStats());
        var result = new HeroComparisonDTO(hero1Id, hero2Id, stats1DTO, stats2DTO);

        return ResponseEntity.ok().body(result);
    }
}
