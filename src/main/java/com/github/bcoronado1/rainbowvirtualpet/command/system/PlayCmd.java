package com.github.bcoronado1.rainbowvirtualpet.command.system;

import com.github.bcoronado1.rainbowvirtualpet.command.PetAcmeModelCmd;
import org.acmestudio.acme.element.property.IAcmeProperty;
import org.acmestudio.acme.model.command.IAcmeCommand;
import org.acmestudio.acme.model.command.IAcmePropertyCommand;
import org.sa.rainbow.core.error.RainbowModelException;
import org.sa.rainbow.model.acme.AcmeModelInstance;

import java.util.LinkedList;
import java.util.List;

public class PlayCmd extends PetAcmeModelCmd<IAcmeProperty> {

    public PlayCmd(AcmeModelInstance model, String componentName) {
        super(model, "play", componentName);
    }

    @Override
    protected List<IAcmeCommand<?>> doConstructCommand() throws RainbowModelException {
        return new LinkedList<>();
    }

    @Override
    public IAcmeProperty getResult() throws IllegalStateException {
        return ((IAcmePropertyCommand) m_command).getProperty();
    }
}
