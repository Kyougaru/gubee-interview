package br.com.gubee.interview.core.adapter.out.persistence;

import br.com.gubee.interview.core.adapter.out.persistence.entities.PowerStatsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PowerStatsRepository extends CrudRepository<PowerStatsEntity, UUID> {
}
