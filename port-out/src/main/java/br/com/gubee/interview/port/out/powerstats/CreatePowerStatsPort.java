package br.com.gubee.interview.port.out.powerstats;

import br.com.gubee.interview.model.PowerStats;

public interface CreatePowerStatsPort {
    PowerStats insert(PowerStats powerStats);
}
