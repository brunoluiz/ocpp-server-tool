package ocpp.cp.commands;

import ocpp.cp.ChargePointCommand;
import ocpp.cp._2012._06.AvailabilityType;
import ocpp.cp._2012._06.ChangeAvailabilityRequest;
import ocpp.cp._2012._06.ChangeAvailabilityResponse;
import org.apache.commons.cli.*;

/**
 * Created by bds on 17/11/2016.
 */
public class ChangeAvailabilityCommand extends ChargePointCommand {
    private AvailabilityType type = null;
    private Integer connector = null;

    public ChangeAvailabilityCommand(String parameters) throws Exception {
        super();
        parseParameters(parameters);
    }

    protected void parseParameters(String parameters) throws Exception {
        // create Options object
        Options options = new Options();
        Option type = new Option("t", "type", true, "availability type");
        type.setRequired(true);
        options.addOption(type);

        Option connector = new Option("c", "connector", true, "connector id");
        connector.setRequired(true);
        options.addOption(connector);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");

        try {
            CommandLine cmd = parser.parse(options, parametersOptions);
            String parsedType = cmd.getOptionValue("tag");

            if (parsedType == "inoperative")
                this.type = AvailabilityType.INOPERATIVE;
            else
                this.type = AvailabilityType.OPERATIVE;

            this.connector = Integer.parseInt(cmd.getOptionValue("connector"));
        } catch (ParseException e) {
            printHelp(options);
            throw e;
        }
    }

    public Object execute() throws Exception {
        ChangeAvailabilityRequest request = new ChangeAvailabilityRequest();
        request.setType(type);
        request.setConnectorId(connector);

        ChangeAvailabilityResponse response = chargePointService.changeAvailability(request, chargeBoxId);
        return response;
    }
}
