package br.com.gubee.interview.core.stubs;

import br.com.gubee.interview.core.domain.service.ManageHeroService;
import br.com.gubee.interview.core.domain.service.ManagePowerStatsService;
import br.com.gubee.interview.core.port.out.hero.CreateHeroPort;
import br.com.gubee.interview.core.port.out.hero.DeleteHeroPort;
import br.com.gubee.interview.core.port.out.hero.LoadHeroPort;
import br.com.gubee.interview.core.port.out.hero.UpdateHeroPort;

public class HeroServiceStub extends ManageHeroService {
    public HeroServiceStub(LoadHeroPort loadHeroPort, CreateHeroPort createHeroPort, UpdateHeroPort updateHeroPort, DeleteHeroPort deleteHeroPort, ManagePowerStatsService managePowerStatsService) {
        super(loadHeroPort, createHeroPort, updateHeroPort, deleteHeroPort, managePowerStatsService);
    }
}
