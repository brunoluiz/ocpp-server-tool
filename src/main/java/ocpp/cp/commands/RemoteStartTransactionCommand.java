package ocpp.cp.commands;

import ocpp.cp.ChargePointCommand;
import ocpp.cp._2012._06.RemoteStartTransactionRequest;
import ocpp.cp._2012._06.RemoteStartTransactionResponse;
import org.apache.commons.cli.*;

/**
 * Created by bds on 09/11/2016.
 */
public class RemoteStartTransactionCommand extends ChargePointCommand {
    private String tag = null;
    private Integer connector = null;

    public RemoteStartTransactionCommand(String parameters) {
        super();
        parseParameters(parameters);
    }

    protected void parseParameters(String parameters) {
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
        CommandLine cmd;
        try {
            cmd = parser.parse(options, parametersOptions);
            this.tag = cmd.getOptionValue("tag");
            this.connector = Integer.parseInt(cmd.getOptionValue("connector"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Object execute() throws Exception {
        RemoteStartTransactionRequest parameters = new RemoteStartTransactionRequest();
        parameters.setConnectorId(connector);
        parameters.setIdTag(tag);

        RemoteStartTransactionResponse response = chargePointService.remoteStartTransaction(parameters, "");
        return response;
    }
}
