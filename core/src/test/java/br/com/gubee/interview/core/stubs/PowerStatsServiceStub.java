package br.com.gubee.interview.core.stubs;

import br.com.gubee.interview.core.domain.service.ManagePowerStatsService;
import br.com.gubee.interview.core.port.out.powerstats.CreatePowerStatsPort;
import br.com.gubee.interview.core.port.out.powerstats.DeletePowerStatsPort;
import br.com.gubee.interview.core.port.out.powerstats.LoadPowerStatsPort;
import br.com.gubee.interview.core.port.out.powerstats.UpdatePowerStatsPort;

public class PowerStatsServiceStub extends ManagePowerStatsService {
    public PowerStatsServiceStub(LoadPowerStatsPort loadPowerStatsPort, CreatePowerStatsPort createPowerStatsPort, UpdatePowerStatsPort updatePowerStatsPort, DeletePowerStatsPort deletePowerStatsPort) {
        super(loadPowerStatsPort, createPowerStatsPort, updatePowerStatsPort, deletePowerStatsPort);
    }
}
