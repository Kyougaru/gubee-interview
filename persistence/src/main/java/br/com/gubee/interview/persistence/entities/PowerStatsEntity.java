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
@Table("power_stats")
public class PowerStatsEntity {
    @Id
    private UUID id;
    private Short strength;
    private Short agility;
    private Short dexterity;
    private Short intelligence;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
