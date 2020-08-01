package com.github.bcoronado1.rainbowvirtualpet.command.model;

import org.sa.rainbow.model.acme.AcmeModelInstance;

public class SetHungerCmd extends SetDoubleCmd {
    public SetHungerCmd(AcmeModelInstance model, String componentName, String valueString) {
        super(model, "setHunger", componentName, "hunger", valueString);
    }
}
