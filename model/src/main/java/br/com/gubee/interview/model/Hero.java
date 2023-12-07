package br.com.gubee.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hero {
    public enum Race {
        HUMAN, ALIEN, DIVINE, CYBORG
    }

    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private Race race;

    @Valid
    private PowerStats powerStats;

    @NotNull
    private Boolean enabled;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
