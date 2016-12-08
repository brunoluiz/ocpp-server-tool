package ocpp.cp.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import ocpp.OcppCommand;
import ocpp.cp._2012._06.AvailabilityType;
import ocpp.cp._2012._06.ChangeAvailabilityRequest;
import ocpp.cp._2012._06.ChangeAvailabilityResponse;
import ocpp.cp._2012._06.ChargePointService;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bds on 17/11/2016.
 */
public class ChangeAvailabilityCommand implements OcppCommand {
    private final Logger log = LoggerFactory.getLogger(ChangeAvailabilityCommand.class);

    private final ChargePointService chargePointService;
    private AvailabilityType type = null;
    private Integer connector = null;
    private String chargeBoxId = null;

    @Inject
    public ChangeAvailabilityCommand(@Assisted String parameters,
                                     ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) throws Exception {
        // create Options object
        Options options = new Options();
        Option type = new Option("t", "type", true, "availability type: Operative|Innoperative");
        type.setRequired(true);
        options.addOption(type);

        Option connector = new Option("c", "connector", true, "connector id");
        connector.setRequired(true);
        options.addOption(connector);

        Option cpid = new Option("cpid", "charge point id", true, "charge point id");
        cpid.setRequired(true);
        options.addOption(cpid);

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
            this.chargeBoxId = cmd.getOptionValue("cpid");
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
        log.info("Received status: {}", response.getStatus());

        return response;
    }
}
