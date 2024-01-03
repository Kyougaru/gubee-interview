package br.com.gubee.interview.port.in.powerstats;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface UpdatePowerStatsUseCase {
    void update(PowerStats updatedPowerStats, UUID id);
}
