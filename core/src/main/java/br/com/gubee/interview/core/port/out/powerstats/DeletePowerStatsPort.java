package br.com.gubee.interview.core.port.out.powerstats;

import java.util.UUID;

public interface DeletePowerStatsPort {
    void deleteById(UUID id);
}
