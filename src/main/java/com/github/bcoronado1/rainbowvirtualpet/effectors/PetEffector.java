package com.github.bcoronado1.rainbowvirtualpet.effectors;

import org.apache.log4j.Logger;
import org.sa.rainbow.translator.effectors.AbstractEffector;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQException;

import java.util.List;

/**
 * The PetEffector sends requests to the JavaVirtualPet simulation to effect pet state. Requests are configured in the
 * effectors configuration file (effectors.yml) as a parameter.
 */
public class PetEffector extends AbstractEffector {

    private static final Logger LOGGER = Logger.getLogger(PetEffector.class);
    private static final ZContext context = new ZContext();
    private ZMQ.Socket reqSocket = context.createSocket(SocketType.REQ);
    private String address, topic;

    public PetEffector(String refID, String name, String[] args) {
        super(refID, name, Kind.JAVA);
        this.address = args[0];
        this.topic = args[1];
        reqSocket.setReceiveTimeOut(5000);
        reqSocket.connect(String.format("tcp://%s", this.address));
    }

    @Override
    public Outcome execute(List<String> args) {
        try {
            reqSocket.send(this.topic.getBytes());
            LOGGER.info(String.format("Sent request: %s", this.topic));
            String response = reqSocket.recvStr();
            LOGGER.info(String.format("Received response: %s", response));
            return Outcome.SUCCESS;
        } catch (ZMQException e) {
            LOGGER.error(e.toString());
            return Outcome.FAILURE;
        }
    }
}
