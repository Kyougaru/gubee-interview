package br.com.gubee.interview.web.adapter.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class PowerStatsDTO {
    @NotNull
    private Short strength;

    @NotNull
    private Short agility;

    @NotNull
    private Short dexterity;

    @NotNull
    private Short intelligence;
}
