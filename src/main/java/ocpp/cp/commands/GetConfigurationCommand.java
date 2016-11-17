package ocpp.cp.commands;

import ocpp.cp.ChargePointCommand;
import ocpp.cp._2012._06.GetConfigurationRequest;
import ocpp.cp._2012._06.GetConfigurationResponse;
import org.apache.commons.cli.*;

public class GetConfigurationCommand extends ChargePointCommand {
    private String key = null;

    public GetConfigurationCommand(String parameters) throws Exception {
        super();
        parseParameters(parameters);
    }

    protected void parseParameters(String parameters) throws Exception {
        // create Options object
        Options options = new Options();
        Option type = new Option("k", "key", true, "key");
        type.setRequired(false);
        options.addOption(type);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");

        try {
            CommandLine cmd = parser.parse(options, parametersOptions);
            this.key = cmd.getOptionValue("key");
        } catch (ParseException e) {
            printHelp(options);
            throw e;
        }
    }

    public Object execute() throws Exception {
        GetConfigurationRequest request = new GetConfigurationRequest();

        GetConfigurationResponse response = chargePointService.getConfiguration(request, chargeBoxId);
        return response;
    }
}