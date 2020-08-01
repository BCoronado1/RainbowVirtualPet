package com.github.bcoronado1.rainbowvirtualpet.gauges;

import org.sa.rainbow.core.error.RainbowException;
import org.sa.rainbow.core.gauges.RegularPatternGauge;
import org.sa.rainbow.core.models.commands.IRainbowOperation;
import org.sa.rainbow.core.util.TypedAttribute;
import org.sa.rainbow.core.util.TypedAttributeWithValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The PetGauge uses configured regular expressions to match information from probe reports. Regular expressions
 * are configured in the gauges configuration file (gauges.yml) as a parameter. The convention for data received from
 * probes is property:value (e.g. hunger:20.0).
 */
public class PetGauge extends RegularPatternGauge {

    public static final String NAME = "G - PetGauge";
    private static final String DEFAULT = "DEFAULT";
    private String regex;

    public PetGauge(String id, long beaconPeriod, TypedAttribute gaugeDesc, TypedAttribute modelDesc,
                    List<TypedAttributeWithValue> setupParams, Map<String, IRainbowOperation> mappings)
            throws RainbowException {
        super(NAME, id, beaconPeriod, gaugeDesc, modelDesc, setupParams, mappings);
        for (TypedAttributeWithValue att : setupParams) {
            if (Objects.equals(att.getName(), "regex")) {
                regex = (String) att.getValue();
                break;
            }
        }
        if (regex == null) {
            throw new RainbowException("Gauge regular expression is not set up properly.");
        }
        addPattern(DEFAULT, Pattern.compile(regex));
    }

    @Override
    protected void doMatch(String s, Matcher matcher) {
        String line = matcher.group(0);
        String value = line.split(":")[1]; // Get the value after the colon. e.g. property:value
        String commandName = m_commands.keySet().iterator().next();
        m_reportingPort.trace(getComponentType(),
                String.format("Updating component property using command %s and value %s", commandName, value));

        // This is the standard convention for publishing model updates. We get the command associated with this gauge
        // and build a parameter map, then use the AbstractGauge's issueCommand method.
        IRainbowOperation cmd = getCommand(commandName);
        Map<String, String> parameterMap = new HashMap<>();
        String[] params = cmd.getParameters();
        if (params.length > 0) {
            parameterMap.put(cmd.getParameters()[0], value);
            issueCommand(cmd, parameterMap);
        }
    }
}
