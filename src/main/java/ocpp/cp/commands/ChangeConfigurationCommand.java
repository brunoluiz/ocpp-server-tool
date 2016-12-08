package ocpp.cp.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import ocpp.OcppCommand;
import ocpp.cp._2012._06.ChangeConfigurationRequest;
import ocpp.cp._2012._06.ChangeConfigurationResponse;
import ocpp.cp._2012._06.ChargePointService;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeConfigurationCommand implements OcppCommand {
    private final Logger log = LoggerFactory.getLogger(ChangeConfigurationCommand.class);

    private final ChargePointService chargePointService;
    private String key = null;
    private String value = null;
    private String chargeBoxId = "";

    @Inject
    public ChangeConfigurationCommand(@Assisted String parameters,
                                      ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) throws Exception {
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

        try {
            CommandLine cmd = parser.parse(options, parametersOptions);
            this.key = cmd.getOptionValue("key");
            this.value = cmd.getOptionValue("value");
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getSimpleName(), options);
            throw e;
        }
    }

    public Object execute() throws Exception {
        ChangeConfigurationRequest request = new ChangeConfigurationRequest();
        request.setKey(key);
        request.setValue(value);

        ChangeConfigurationResponse response = chargePointService.changeConfiguration(request, chargeBoxId);
        log.info("Received status: {}", response.getStatus());

        return response;
    }
}