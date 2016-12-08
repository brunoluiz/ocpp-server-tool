package ocpp.cp.commands;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import ocpp.OcppCommand;
import ocpp.cp._2012._06.ChargePointService;
import ocpp.cp._2012._06.RemoteStopTransactionRequest;
import ocpp.cp._2012._06.RemoteStopTransactionResponse;
import org.apache.commons.cli.*;

/**
 * Created by bds on 09/11/2016.
 */
public class RemoteStopTransactionCommand implements OcppCommand {
    private Integer transaction = null;
    private ChargePointService chargePointService;
    private String chargeBoxId = "";

    @Inject
    public RemoteStopTransactionCommand(@Assisted String parameters,
                                        ChargePointService chargePointService) throws Exception {
        this.chargePointService = chargePointService;
        parseParameters(parameters);
    }

    protected void parseParameters(String parameters) throws Exception {
        // create Options object
        Options options = new Options();
        Option tag = new Option("tid", "transaction", true, "transaction id");
        tag.setRequired(true);
        options.addOption(tag);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");
        try {
            CommandLine cmd = parser.parse(options, parametersOptions);
            this.transaction = Integer.parseInt(cmd.getOptionValue("transaction"));
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp(getClass().getSimpleName(), options);
            throw e;
        }
    }

    public Object execute() throws Exception {
        RemoteStopTransactionRequest parameters = new RemoteStopTransactionRequest();
        parameters.setTransactionId(transaction);

        RemoteStopTransactionResponse response = chargePointService.remoteStopTransaction(parameters, chargeBoxId);
        return response;
    }
}
