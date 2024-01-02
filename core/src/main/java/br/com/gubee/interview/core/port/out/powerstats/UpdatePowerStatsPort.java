package br.com.gubee.interview.core.port.out.powerstats;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface UpdatePowerStatsPort {
    void update(PowerStats updatedPowerStats, UUID id);
}
