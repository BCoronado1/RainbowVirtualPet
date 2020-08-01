package com.github.bcoronado1.rainbowvirtualpet.command.model;

import com.github.bcoronado1.rainbowvirtualpet.command.PetAcmeModelCmd;
import org.acmestudio.acme.PropertyHelper;
import org.acmestudio.acme.core.type.IAcmeStringValue;
import org.acmestudio.acme.element.IAcmeComponent;
import org.acmestudio.acme.element.property.IAcmeProperty;
import org.acmestudio.acme.model.command.IAcmeCommand;
import org.acmestudio.acme.model.command.IAcmePropertyCommand;
import org.sa.rainbow.core.error.RainbowModelException;
import org.sa.rainbow.model.acme.AcmeModelInstance;

import java.text.MessageFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * Generic command to set a string value in the Acme model specific to the PetSys system.
 */
public class SetStringCmd extends PetAcmeModelCmd<IAcmeProperty> {

    protected String componentName;
    protected String propertyName;
    protected String value;

    public SetStringCmd(AcmeModelInstance model, String commandName, String componentName, String propertyName, String valueString) {
        super(model, commandName, componentName, valueString);
        this.componentName = componentName;
        this.propertyName = propertyName;
        this.value = valueString;
    }

    @Override
    protected List<IAcmeCommand<?>> doConstructCommand() throws RainbowModelException {
        IAcmeComponent pet = getModelContext().resolveInModel(this.componentName, IAcmeComponent.class);
        if (pet == null)
            throw new RainbowModelException(MessageFormat.format(
                    "The component %s could not be found in the model.", getTarget()));
        IAcmeProperty property = pet.getProperty(this.propertyName);
        if (property == null)
            throw new RainbowModelException(MessageFormat.format(
                    "The component %s does not have a property %s.",
                    getTarget(), this.propertyName));
        try {
            IAcmeStringValue acmeVal = PropertyHelper.toAcmeVal(this.value);
            List<IAcmeCommand<?>> cmds = new LinkedList<>();
            if (propertyValueChanging(property, acmeVal)) {
                m_command = pet.getCommandFactory().propertyValueSetCommand(property, acmeVal);
                cmds.add(m_command);
            }
            return cmds;
        } catch (Exception e) {
            throw new RainbowModelException(MessageFormat.format("Error setting Acme model property %s.%s: %s",
                    getTarget(), this.propertyName, e.getMessage()));
        }
    }

    @Override
    public IAcmeProperty getResult() {
        return ((IAcmePropertyCommand) m_command).getProperty();
    }
}
