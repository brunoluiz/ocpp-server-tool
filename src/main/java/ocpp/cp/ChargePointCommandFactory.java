package ocpp.cp;

import ocpp.cp.commands.*;

public interface ChargePointCommandFactory {
    CancelReservationCommand createCancelReservation(String parameters);

    ChangeAvailabilityCommand createChangeAvailability(String parameters);

    ChangeConfigurationCommand createChangeConfiguration(String parameters);

    GetConfigurationCommand createGetConfiguration(String parameters);

    GetLocalListVersionCommand createGetLocalListVersion(String parameters);

    RemoteStartTransactionCommand createRemoteStartTransaction(String parameters);

    RemoteStopTransactionCommand createRemoteStopTransaction(String parameters);

    UnlockConnectorCommand createUnlockConnector(String parameters);

    ReserveNowCommand createReserveNow(String parameters);

    ResetCommand createReset(String parameters);

    DataTransferCommand createDataTransfer(String parameters);

    ClearCacheCommand createClearCache(String parameters);
}
