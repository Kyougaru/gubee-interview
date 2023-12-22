package br.com.gubee.interview.core.stubs;

import br.com.gubee.interview.core.features.powerstats.PowerStatsService;

public class PowerStatsServiceStub extends PowerStatsService {
    public PowerStatsServiceStub() {
        super.repository = new PowerStatsRepositoryStub();
    }
}
