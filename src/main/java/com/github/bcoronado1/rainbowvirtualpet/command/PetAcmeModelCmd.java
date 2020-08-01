package com.github.bcoronado1.rainbowvirtualpet.command;

import org.acmestudio.acme.element.IAcmeSystem;
import org.sa.rainbow.model.acme.AcmeModelInstance;
import org.sa.rainbow.model.acme.AcmeModelOperation;

public abstract class PetAcmeModelCmd<T> extends AcmeModelOperation<T> {

    public PetAcmeModelCmd(AcmeModelInstance model, String commandName, String targetName, String... parameters) {
        super(commandName, model, targetName, parameters);
    }

    @Override
    protected boolean checkModelValidForCommand(IAcmeSystem model) {
        return model.declaresType("PetFam");
    }
}
