package br.com.gubee.interview.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("hero")
public class HeroEntity {
    @Id
    private UUID id;
    private String name;
    private String race;

    private UUID powerStatsId;

    private Boolean enabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
