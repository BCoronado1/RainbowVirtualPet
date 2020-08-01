package com.github.bcoronado1.rainbowvirtualpet.command;

import org.acmestudio.acme.element.IAcmeSystem;
import org.sa.rainbow.model.acme.AcmeModelCommandFactory;
import org.sa.rainbow.model.acme.AcmeModelInstance;

public class PetAcmeModelInstance extends AcmeModelInstance {

    private PetCommandFactory factory;

    public PetAcmeModelInstance(IAcmeSystem system, String source) {
        super(system, source);
    }

    @Override
    protected AcmeModelInstance generateInstance(IAcmeSystem iAcmeSystem) {
        return new PetAcmeModelInstance(iAcmeSystem, getOriginalSource());
    }

    @Override
    public AcmeModelCommandFactory getCommandFactory() {
        if (this.factory == null) {
            this.factory = new PetCommandFactory(this);
        }
        return this.factory;
    }
}