package com.github.bcoronado1.rainbowvirtualpet.command;

import com.github.bcoronado1.rainbowvirtualpet.command.model.SetFatigueCmd;
import com.github.bcoronado1.rainbowvirtualpet.command.model.SetHungerCmd;
import com.github.bcoronado1.rainbowvirtualpet.command.model.SetNameCmd;
import com.github.bcoronado1.rainbowvirtualpet.command.model.SetStressCmd;
import com.github.bcoronado1.rainbowvirtualpet.command.system.FeedCmd;
import com.github.bcoronado1.rainbowvirtualpet.command.system.PlayCmd;
import com.github.bcoronado1.rainbowvirtualpet.command.system.RestCmd;
import org.sa.rainbow.core.models.ModelsManager;
import org.sa.rainbow.model.acme.AcmeModelCommandFactory;
import org.sa.rainbow.model.acme.AcmeModelInstance;

import java.io.InputStream;

public class PetCommandFactory extends AcmeModelCommandFactory {

    public static PetLoadModelCommand loadCommand(ModelsManager modelsManager,
                                                  String modelName,
                                                  InputStream stream,
                                                  String source) {
        return new PetLoadModelCommand(modelName, modelsManager, stream, source);
    }

    public PetCommandFactory(AcmeModelInstance model) {
        super(model);
    }

    @Override
    protected void fillInCommandMap() {
        super.fillInCommandMap();
        m_commandMap.put("setName".toLowerCase(), SetNameCmd.class);
        m_commandMap.put("setHunger".toLowerCase(), SetHungerCmd.class);
        m_commandMap.put("setFatigue".toLowerCase(), SetFatigueCmd.class);
        m_commandMap.put("setStress".toLowerCase(), SetStressCmd.class);
        m_commandMap.put("feed".toLowerCase(), FeedCmd.class);
        m_commandMap.put("rest".toLowerCase(), RestCmd.class);
        m_commandMap.put("play".toLowerCase(), PlayCmd.class);
    }

    public FeedCmd feedCmd() {
        return new FeedCmd((AcmeModelInstance) m_modelInstance, "pet");
    }

    public RestCmd restCmd() {
        return new RestCmd((AcmeModelInstance) m_modelInstance, "pet");
    }

    public PlayCmd playCmd() {
        return new PlayCmd((AcmeModelInstance) m_modelInstance, "pet");
    }
}
