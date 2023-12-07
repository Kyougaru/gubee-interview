package br.com.gubee.interview.core.features.powerstats;

import br.com.gubee.interview.core.entities.PowerStatsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PowerStatsRepository extends CrudRepository<PowerStatsEntity, UUID> {
}
