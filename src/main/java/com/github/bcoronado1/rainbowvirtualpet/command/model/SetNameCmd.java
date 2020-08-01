package com.github.bcoronado1.rainbowvirtualpet.command.model;

import org.sa.rainbow.model.acme.AcmeModelInstance;

public class SetNameCmd extends SetStringCmd {
    public SetNameCmd(AcmeModelInstance model, String componentName, String valueString) {
        super(model, "setName", componentName, "name", valueString);
    }
}