package com.github.bcoronado1.rainbowvirtualpet.command.model;

import org.sa.rainbow.model.acme.AcmeModelInstance;

public class SetStressCmd extends SetDoubleCmd {
    public SetStressCmd(AcmeModelInstance model, String componentName, String valueString) {
        super(model, "setStress", componentName, "stress", valueString);
    }
}
