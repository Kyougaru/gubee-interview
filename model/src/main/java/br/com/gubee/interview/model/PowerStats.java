package br.com.gubee.interview.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PowerStats {
    private UUID id;

    @NotNull
    private Short strength;

    @NotNull
    private Short agility;

    @NotNull
    private Short dexterity;

    @NotNull
    private Short intelligence;

    private Timestamp createdAt;
    private Timestamp updatedAt;
}
