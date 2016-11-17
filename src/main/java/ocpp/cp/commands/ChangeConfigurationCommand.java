package ocpp.cp.commands;

import ocpp.cp.ChargePointCommand;
import ocpp.cp._2012._06.ChangeConfigurationRequest;
import ocpp.cp._2012._06.ChangeConfigurationResponse;
import org.apache.commons.cli.*;

public class ChangeConfigurationCommand extends ChargePointCommand {
    private String key = null;
    private String value = null;

    public ChangeConfigurationCommand(String parameters) {
        super();
        parseParameters(parameters);
    }

    protected void parseParameters(String parameters) {
        // create Options object
        Options options = new Options();
        Option type = new Option("k", "key", true, "key");
        type.setRequired(true);
        options.addOption(type);

        Option connector = new Option("v", "value", true, "value");
        connector.setRequired(true);
        options.addOption(connector);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");
        CommandLine cmd;
        try {
            cmd = parser.parse(options, parametersOptions);
            this.key   = cmd.getOptionValue("key");
            this.value = cmd.getOptionValue("value");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Object execute() throws Exception {
        ChangeConfigurationRequest request = new ChangeConfigurationRequest();
        request.setKey(key);
        request.setValue(value);

        ChangeConfigurationResponse response = chargePointService.changeConfiguration(request, chargeBoxId);
        return response;
    }
}