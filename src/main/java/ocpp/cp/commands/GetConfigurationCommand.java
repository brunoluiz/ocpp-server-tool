package ocpp.cp.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import ocpp.OcppCommand;
import ocpp.cp._2012._06.ChargePointService;
import ocpp.cp._2012._06.GetConfigurationRequest;
import ocpp.cp._2012._06.GetConfigurationResponse;
import org.apache.commons.cli.*;

public class GetConfigurationCommand implements OcppCommand {
    private String key = null;
    private ChargePointService chargePointService;
    private String chargeBoxId = "";

    @Inject
    public GetConfigurationCommand(@Assisted String parameters,
                                   ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
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
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getSimpleName(), options);
            throw e;
        }
    }

    public Object execute() throws Exception {
        GetConfigurationRequest request = new GetConfigurationRequest();

        GetConfigurationResponse response = chargePointService.getConfiguration(request, chargeBoxId);
        return response;
    }
}