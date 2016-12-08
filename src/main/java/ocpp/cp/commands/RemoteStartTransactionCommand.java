package ocpp.cp.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import ocpp.OcppCommand;
import ocpp.cp._2012._06.ChargePointService;
import ocpp.cp._2012._06.RemoteStartTransactionRequest;
import ocpp.cp._2012._06.RemoteStartTransactionResponse;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bds on 09/11/2016.
 */
public class RemoteStartTransactionCommand implements OcppCommand {
    private final Logger log = LoggerFactory.getLogger(ChangeAvailabilityCommand.class);

    private final ChargePointService chargePointService;
    private String tag = null;
    private Integer connector = null;
    private String chargeBoxId = "";

    @Inject
    public RemoteStartTransactionCommand(@Assisted String parameters,
                                         ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
        parseParameters(parameters);
    }

    private void parseParameters(String parameters) throws Exception {
        // create Options object
        Options options = new Options();
        Option tag = new Option("t", "tag", true, "tagid");
        tag.setRequired(true);
        options.addOption(tag);

        Option connector = new Option("c", "connector", true, "connector id");
        connector.setRequired(true);
        options.addOption(connector);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");

        try {
            CommandLine cmd = parser.parse(options, parametersOptions);
            this.tag = cmd.getOptionValue("tag");
            this.connector = Integer.parseInt(cmd.getOptionValue("connector"));
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getSimpleName(), options);
            throw e;
        }
    }

    public Object execute() throws Exception {
        RemoteStartTransactionRequest parameters = new RemoteStartTransactionRequest();
        parameters.setConnectorId(connector);
        parameters.setIdTag(tag);

        RemoteStartTransactionResponse response = chargePointService.remoteStartTransaction(parameters, chargeBoxId);
        log.info("Received status: {}", response.getStatus());

        return response;
    }
}
