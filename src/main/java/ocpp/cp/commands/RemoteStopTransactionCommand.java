package ocpp.cp.commands;

import ocpp.cp.ChargePointCommand;
import ocpp.cp._2012._06.RemoteStopTransactionRequest;
import ocpp.cp._2012._06.RemoteStopTransactionResponse;
import org.apache.commons.cli.*;

/**
 * Created by bds on 09/11/2016.
 */
public class RemoteStopTransactionCommand extends ChargePointCommand {
    private Integer transaction = null;

    public RemoteStopTransactionCommand(String parameters) {
        super();

        // create Options object
        Options options = new Options();
        Option tag = new Option("tid", "transaction", true, "transaction id");
        tag.setRequired(true);
        options.addOption(tag);

        CommandLineParser parser = new DefaultParser();
        String[] parametersOptions = parameters.split(" ");
        CommandLine cmd;
        try {
            cmd = parser.parse(options, parametersOptions);
            this.transaction = Integer.parseInt(cmd.getOptionValue("transaction"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Object execute() throws Exception {
        RemoteStopTransactionRequest parameters = new RemoteStopTransactionRequest();
        parameters.setTransactionId(transaction);

        RemoteStopTransactionResponse response = chargePointService.remoteStopTransaction(parameters, "");
        return response;
    }
}
