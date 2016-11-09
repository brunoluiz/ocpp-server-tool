package ocpp.cp.commands;

import ocpp.cp.ChargePointCommand;
import ocpp.cp._2012._06.RemoteStopTransactionRequest;
import ocpp.cp._2012._06.RemoteStopTransactionResponse;

/**
 * Created by bds on 09/11/2016.
 */
public class RemoteStopTransactionCommand extends ChargePointCommand {
    private Integer transactionId = null;

    public RemoteStopTransactionCommand(String parameters) {
        super();
    }

    public Object execute() throws Exception {
        RemoteStopTransactionRequest parameters = new RemoteStopTransactionRequest();
        parameters.setTransactionId(transactionId);

        RemoteStopTransactionResponse response = chargePointService.remoteStopTransaction(parameters);
        return response;
    }
}
