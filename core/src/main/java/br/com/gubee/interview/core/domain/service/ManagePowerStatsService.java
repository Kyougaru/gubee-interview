package br.com.gubee.interview.core.domain.service;

import br.com.gubee.interview.core.port.in.powerstats.*;
import br.com.gubee.interview.core.port.out.powerstats.CreatePowerStatsPort;
import br.com.gubee.interview.core.port.out.powerstats.DeletePowerStatsPort;
import br.com.gubee.interview.core.port.out.powerstats.LoadPowerStatsPort;
import br.com.gubee.interview.core.port.out.powerstats.UpdatePowerStatsPort;
import br.com.gubee.interview.model.PowerStats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ManagePowerStatsService implements FindByIdPowerStatsUseCase, InsertPowerStatsUseCase, UpdatePowerStatsUseCase, DeletePowerStatsUseCase {
    private final LoadPowerStatsPort loadPowerStatsPort;
    private final CreatePowerStatsPort createPowerStatsPort;
    private final UpdatePowerStatsPort updatePowerStatsPort;
    private final DeletePowerStatsPort deletePowerStatsPort;
    
    @Override
    public PowerStats findById(UUID id) {
        return loadPowerStatsPort.findById(id);
    }

    @Override
    public PowerStats insert(PowerStats powerStats) {
        var instant = Timestamp.from(Instant.now());
        powerStats.setCreatedAt(instant);
        powerStats.setUpdatedAt(instant);

        return createPowerStatsPort.insert(powerStats);
    }

    @Override
    public void update(PowerStats updatedPowerStats, UUID id) {
        var instant = Timestamp.from(Instant.now());
        updatedPowerStats.setUpdatedAt(instant);

        updatePowerStatsPort.update(updatedPowerStats, id);
    }

    @Override
    public void delete(UUID id) {
        deletePowerStatsPort.deleteById(id);
    }
}
