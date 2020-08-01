package com.github.bcoronado1.rainbowvirtualpet.command;

import org.acmestudio.acme.element.IAcmeSystem;
import org.sa.rainbow.core.models.IModelsManager;
import org.sa.rainbow.model.acme.AbstractAcmeLoadModelCmd;
import org.sa.rainbow.model.acme.AcmeModelInstance;

import java.io.InputStream;

public class PetLoadModelCommand extends AbstractAcmeLoadModelCmd {

    public PetLoadModelCommand(String systemName, IModelsManager mm, InputStream is, String source) {
        super(systemName, mm, is, source);
    }

    @Override
    protected AcmeModelInstance createAcmeModelInstance(IAcmeSystem system) {
        return new PetAcmeModelInstance(system, getOriginalSource());
    }
}
