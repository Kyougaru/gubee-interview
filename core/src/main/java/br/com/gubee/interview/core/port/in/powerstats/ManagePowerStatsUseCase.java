package br.com.gubee.interview.core.port.in.powerstats;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface ManagePowerStatsUseCase {
    PowerStats findById(UUID id);
    PowerStats insert(PowerStats powerStats);
    void update(PowerStats updatedPowerStats, UUID id);
    void delete(UUID id);
}
