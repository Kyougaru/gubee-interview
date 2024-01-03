package br.com.gubee.interview.core.port.in.powerstats;

import br.com.gubee.interview.model.PowerStats;

public interface InsertPowerStatsUseCase {
    PowerStats insert(PowerStats powerStats);
}
