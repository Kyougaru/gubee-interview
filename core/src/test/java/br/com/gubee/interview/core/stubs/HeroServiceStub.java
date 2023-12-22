package br.com.gubee.interview.core.stubs;

import br.com.gubee.interview.core.features.hero.HeroService;

public class HeroServiceStub extends HeroService {
    public HeroServiceStub() {
        super.repository = new HeroRepositoryStub();
    }
}
