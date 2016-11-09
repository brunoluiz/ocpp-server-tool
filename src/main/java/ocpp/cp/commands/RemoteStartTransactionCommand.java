package ocpp.cp.commands;

import ocpp.cp.ChargePointCommand;
import ocpp.cp._2012._06.RemoteStartTransactionRequest;
import ocpp.cp._2012._06.RemoteStartTransactionResponse;

/**
 * Created by bds on 09/11/2016.
 */
public class RemoteStartTransactionCommand extends ChargePointCommand {
    private String tagId = null;
    private Integer connector = null;

    public RemoteStartTransactionCommand(String parameters) {
        super();
    }

    public Object execute() throws Exception {
        RemoteStartTransactionRequest parameters = new RemoteStartTransactionRequest();
        parameters.setConnectorId(connector);
        parameters.setIdTag(tagId);

        RemoteStartTransactionResponse response = chargePointService.remoteStartTransaction(parameters);
        return response;
    }
}
