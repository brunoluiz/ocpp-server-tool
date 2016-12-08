package ocpp.cp.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import ocpp.OcppCommand;
import ocpp.cp._2012._06.AvailabilityType;
import ocpp.cp._2012._06.ChangeAvailabilityRequest;
import ocpp.cp._2012._06.ChangeAvailabilityResponse;
import ocpp.cp._2012._06.ChargePointService;
import org.apache.commons.cli.*;

/**
 * Created by bds on 17/11/2016.
 */
public class ChangeAvailabilityCommand implements OcppCommand {
    private AvailabilityType type = null;
    private Integer connector = null;
    private ChargePointService chargePointService;
    private String chargeBoxId = "";

    @Inject
    public ChangeAvailabilityCommand(@Assisted String parameters,
                                     ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
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
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getSimpleName(), options);
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
