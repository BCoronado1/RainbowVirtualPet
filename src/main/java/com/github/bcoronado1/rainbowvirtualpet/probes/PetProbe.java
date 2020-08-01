package com.github.bcoronado1.rainbowvirtualpet.probes;

import com.google.gson.Gson;
import org.sa.rainbow.translator.probes.AbstractRunnableProbe;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

import java.util.Objects;

/**
 * The PetProbe class connects to the JavaVirtualPet simulation to continuously send requests for the state of the pet.
 * The state of the pet is cached and compared against new values. The probe will publish a probe report if there is
 * new information to report to gauges.
 */
public class PetProbe extends AbstractRunnableProbe {

    private String addressString;
    private ZContext context = new ZContext();
    private ZMQ.Socket reqSocket = context.createSocket(SocketType.REQ);
    private PetState state = new PetState(); //Initialize instance variable to track state of the pet

    public PetProbe(String id, long sleepTime, String[] args) {
        super(id, "petprobe", Kind.JAVA, sleepTime);
        this.addressString = args[0];
    }

    @Override
    public void run() {
        reqSocket.connect(String.format("tcp://%s", addressString));
        while (thread() == Thread.currentThread() && isActive()) {
            try {
                reqSocket.send("state".getBytes()); //Send a request to the pet simulator for the current state of the pet
                String response = reqSocket.recvStr();
                PetState newState = new Gson().fromJson(response, PetState.class);

                //Go through all the pet properties and report if the value is different
                //Also update the probe's cache
                if (!Objects.equals(this.state.name, newState.name)) {
                    reportData(String.format("name:%s", newState.name));
                    this.state.name = newState.name;
                }

                if (!Objects.equals(this.state.hunger, newState.hunger)) {
                    reportData(String.format("hunger:%s", newState.hunger));
                    this.state.hunger = newState.hunger;
                }

                if (!Objects.equals(this.state.fatigue, newState.fatigue)) {
                    reportData(String.format("fatigue:%s", newState.fatigue));
                    this.state.fatigue = newState.fatigue;
                }

                if (!Objects.equals(this.state.stress, newState.stress)) {
                    reportData(String.format("stress:%s", newState.stress));
                    this.state.stress = newState.stress;
                }

                Thread.sleep(sleepTime());
            } catch (InterruptedException e) {
                log(e.toString());
            }
        }
    }

    /**
     * Data structure to represent pet state (also makes it easy for Gson deserialization).
     */
    private static class PetState {
        String name;
        Double hunger, fatigue, stress;
    }
}
