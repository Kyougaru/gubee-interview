package br.com.gubee.interview.port.out.powerstats;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface UpdatePowerStatsPort {
    void update(PowerStats updatedPowerStats, UUID id);
}
