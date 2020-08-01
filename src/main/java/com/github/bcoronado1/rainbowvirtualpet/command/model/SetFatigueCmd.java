package com.github.bcoronado1.rainbowvirtualpet.command.model;

import org.sa.rainbow.model.acme.AcmeModelInstance;

public class SetFatigueCmd extends SetDoubleCmd {
    public SetFatigueCmd(AcmeModelInstance model, String componentName, String valueString) {
        super(model, "setFatigue", componentName, "fatigue", valueString);
    }
}
