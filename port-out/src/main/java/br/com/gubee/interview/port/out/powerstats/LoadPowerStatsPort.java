package br.com.gubee.interview.port.out.powerstats;

import br.com.gubee.interview.model.PowerStats;

import java.util.UUID;

public interface LoadPowerStatsPort {
    PowerStats findById(UUID id);
}
