package br.com.gubee.interview.persistence.repository;

import br.com.gubee.interview.persistence.entities.PowerStatsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PowerStatsRepository extends CrudRepository<PowerStatsEntity, UUID> {
}
