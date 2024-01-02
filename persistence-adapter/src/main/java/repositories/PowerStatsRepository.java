package repositories;

import entities.PowerStatsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PowerStatsRepository extends CrudRepository<PowerStatsEntity, UUID> {
}
