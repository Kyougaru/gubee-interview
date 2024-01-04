package br.com.gubee.interview.web.adapter.dtos;

import br.com.gubee.interview.model.PowerStats;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class HeroDTO {
    @NotBlank
    private String name;

    @NotNull
    private String race;

    @Valid
    private PowerStatsDTO powerStats;

    @NotNull
    private Boolean enabled;
}
