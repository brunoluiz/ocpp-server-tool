package ocpp.cp;

import ocpp.cp.commands.*;

/**
 * Created by bds on 08/12/2016.
 */
public interface ChargePointCommandFactory {
    ChangeAvailabilityCommand createChangeAvailability(String parameters);

    ChangeConfigurationCommand createChangeConfiguration(String parameters);

    GetConfigurationCommand createGetConfiguration(String parameters);

    RemoteStartTransactionCommand createRemoteStartTransaction(String parameters);

    RemoteStopTransactionCommand createRemoteStopTransaction(String parameters);

    UnlockConnectorCommand createUnlockConnector(String parameters);

    ResetCommand createReset(String parameters);

    DataTransferCommand createDataTransfer(String parameters);

    ClearCacheCommand createClearCache(String parameters);
}
