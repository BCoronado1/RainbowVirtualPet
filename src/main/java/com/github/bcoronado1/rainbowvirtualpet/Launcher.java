package com.github.bcoronado1.rainbowvirtualpet;

import com.github.bcoronado1.javavirtualpet.VirtualPetSimLauncher;
import org.apache.log4j.Logger;
import org.sa.rainbow.core.AbstractRainbowRunnable;
import org.sa.rainbow.core.Rainbow;
import org.sa.rainbow.core.RainbowMaster;
import org.sa.rainbow.core.error.RainbowException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Launcher {

    private static final Logger LOGGER = Logger.getLogger(Launcher.class);

    public static void main(String[] args) throws RainbowException {
        RainbowMaster.main(args);
        doAdditionalConfig();
        VirtualPetSimLauncher.main(args);
    }

    public static void doAdditionalConfig() {
        try {
            Thread.sleep(3000); // Wait some time to let Rainbow startup...
            RainbowMaster master = (RainbowMaster) Rainbow.instance().getRainbowMaster();

            // Normally we'd want to create a new AdaptationManager but to make this example with the least code possible
            // we use reflection. Here we set the adaptation manager's evaluation period to 1 second rather than the
            // default 10 seconds. (The class and variable are both final and the setSleepTime method is protected so
            // in doing this we avoid writing a whole new class.)
            Method m = AbstractRainbowRunnable.class.getDeclaredMethod("setSleepTime", long.class);
            m.setAccessible(true);
            m.invoke(master.adaptationManagerForModel("PetSys:Acme"), 1000);
            m.setAccessible(false);

            //Sometimes autoStart parameter doesn't work...
            master.startProbes();
        } catch (InterruptedException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(String.format("Error while initializing Rainbow: %s", e.toString()));
            System.exit(1);
        }
    }
}
